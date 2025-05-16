import { Injectable, OnInit } from '@angular/core';
import { Observable, of, startWith, Subject, switchMap, throwError } from 'rxjs';
import { environment } from '../environment';
import { HttpClient } from '@angular/common/http';
import { Produit } from './produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitService{
  private refresh$: Subject<void> = new Subject<void>();
  private API_URL: string = `${ environment.API_URL }/produit`;
  private _produits: Array<Produit> = new Array<Produit>();

  constructor(private http: HttpClient) { }

  public refresh() {
    this.refresh$.next();
  }

  public findAll(): Observable<Produit[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<Produit[]>(this.API_URL))
    );
  }

  public findById(id: number): Observable<Produit> {
    return this.http.get<Produit>(`${ this.API_URL }/${ id }`);
  }

  public save(produit: any) {
    if (produit.id) {
      return this.http.put<Produit>(`${ this.API_URL }/${ produit.id }`, produit, {
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
      });
    }
    
    return this.http.post<Produit>(this.API_URL, produit);
  }
  
  public delete(produit: any) {
    return this.http.delete<void>(`${ this.API_URL }/${ produit.id }`);
  }

  public decrementerStock(produitId: number) {
    return this.findById(produitId).pipe(
      switchMap(produit => {
        if (produit.stock > 0) {
          produit.stock -= 1;
          return this.http.put<Produit>(`${this.API_URL}/${produitId}`, produit);
        } else {
          console.log("Stock insuffisant");
          return of(produit);
        }
      })
    );
  }

  public restaurerStock(produitId: number, quantite: number = 1): Observable<Produit> {
    return this.findById(produitId).pipe(
      switchMap((produit) => {
        if (produit) {
          produit.stock += quantite;
          return this.http.put<Produit>(`${this.API_URL}/${produitId}`, produit);
        } else {
          console.log("Produit introuvable pour restauration du stock");
          return throwError(() => new Error('Produit introuvable'));
        }
      })
    );
  }
}
