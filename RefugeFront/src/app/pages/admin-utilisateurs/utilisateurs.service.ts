import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Utilisateurs } from './utilisateurs';
import { environment } from '../../../environment';

@Injectable({
  providedIn: 'root'
})
export class UtilisateursService {
  private refresh$: Subject<void> = new Subject<void>();
  private API_URL: string = `${ environment.API_URL }/utilisateur`;

  constructor(private http: HttpClient) { }

  public refresh() {
    this.refresh$.next();
  }

  public findAll(): Observable<Utilisateurs[]> {
    // return this.http.get<utilisateurs[]>("http://localhost:8080/api/utilisateurs", {
    //   headers: {
    //     'Authorization': "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzQ1NDk4MjQ0LCJleHAiOjE3NDU1MDE4NDR9.Z59OkTHV204DT4W3LE_IV76a0wYut_lDsL7aQsAlH-6x_qnM_lGZWgO8jJOoifiOJjqkmTVMTsGEnnMMftZfIA"
    //   }
    // });
    
    // return this.http.get<utilisateurs[]>("http://localhost:8080/api/utilisateurs");

    return this.refresh$.pipe(
      // Pour forcer une première initialisation de la liste
      startWith(null),
      
      // Transformer le "void" en Array<Todo> en allant chercher les infos
      switchMap(() => {
        return this.http.get<Utilisateurs[]>(this.API_URL)
      })
    );
  }

  public findById(id: number): Observable<Utilisateurs> {
      return this.http.get<Utilisateurs>(`${ this.API_URL }/${ id }`);
    }


  public save(utilisateurs: any) {
    if (utilisateurs.id) {
      return this.http.put<Utilisateurs>(`${ this.API_URL }/${ utilisateurs.id }`, utilisateurs);
    }
    
    return this.http.post<Utilisateurs>(this.API_URL, utilisateurs);
  }
  
  public delete(utilisateurs: any) {
    return this.http.delete<void>(`${ this.API_URL }/${ utilisateurs.id }`);
  }
}
