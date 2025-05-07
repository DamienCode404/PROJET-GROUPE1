import { HttpClient } from '@angular/common/http';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../../environment';
import { Animal } from './animal';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {
  private refresh$: Subject<void> = new Subject<void>();
  private API_URL: string = `${ environment.API_URL }/animal`;

  constructor(private http: HttpClient) { }

  public refresh() {
    this.refresh$.next();
  }

  public findAll(): Observable<Animal[]> {

    return this.refresh$.pipe(
      // Pour forcer une première initialisation de la liste
      startWith(null),
      
      // Transformer le "void" en Array<Todo> en allant chercher les infos
      switchMap(() => {
        return this.http.get<Animal[]>(this.API_URL)
      })
    );
  }

  public findById(id: number): Observable<Animal> {
    return this.http.get<Animal>(`${ this.API_URL }/${ id }`);
  }

  public save(animal: Animal) {
    if (animal.id) {
      return this.http.put<Animal>(`${ this.API_URL }/${ animal.id }`, animal);
    }
    
    return this.http.post<Animal>(this.API_URL, animal);
  }
  
  public delete(animal: any) {
    return this.http.delete<void>(`${ this.API_URL }/${ animal.id }`);
  }
}
