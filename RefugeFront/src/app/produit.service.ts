import { Injectable, OnInit } from '@angular/core';
import { Observable, of, startWith, Subject, switchMap } from 'rxjs';
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

  constructor(private http: HttpClient) {
    this.loadProduits();
   }

   
   private loadProduits() {
    this.findAll().subscribe({
      next: (produits) => {
        this._produits = produits;
        console.log("Produits chargés :", this._produits);
      },
      error: (err) => {
        console.error("Erreur lors du chargement des produits :", err);
      }
    });
  }

  public refresh() {
    this.refresh$.next();
  }
  public findAll(): Observable<Produit[]> {
    return this.http.get<Produit[]>(this.API_URL).pipe(
      switchMap(produits => {
        this._produits = produits; 
        return of(produits);
      })
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
    const produit = this._produits.find(p => p.id === produitId);
    console.log("Produit à décrémenter:", produit);

    if (produit && produit.stock > 0) {
      produit.stock -= 1;
      return this.http.put<Produit>(`${this.API_URL}/${produitId}`, produit);
    } else {
      console.log("Produit introuvable ou stock insuffisant");
      return of(produit!);
    }
  }

  public restaurerStock(produitId: number, quantite: number = 1) {
    const produit = this._produits.find(p => p.id === produitId);
    console.log("Produit à restaurer:", produit);
  
    if (produit) {
      produit.stock += quantite;
      return this.http.put<Produit>(`${this.API_URL}/${produitId}`, produit);
    } else {
      console.log("Produit introuvable pour restauration du stock");
      return of(produit!);
    }
  }
}
