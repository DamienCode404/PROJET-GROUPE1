

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Client } from './client'; 
import { Observable } from 'rxjs';
import { environment } from '../../../environment'; 

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {
  private API_URL = `${environment.API_URL}/inscription`; 

  constructor(private http: HttpClient) {}

  inscrire(client: Client): Observable<Client> {
    return this.http.post<Client>(this.API_URL, client);
  }
}
