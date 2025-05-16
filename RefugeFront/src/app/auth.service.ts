import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from './auth-response';
import { AuthRequest } from './auth-request';
import { environment } from '../environment';
import { Router } from '@angular/router';
import { firstValueFrom, tap } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public token: string = "";
  public user!:any;
  private API_URL: string = `${ environment.API_URL }/connexion`;
  
  constructor(private http: HttpClient, private router: Router) {
    this.token = localStorage.getItem('token') as string;
    this.user = JSON.parse(localStorage.getItem('user') as string) as any;
    this.token = sessionStorage.getItem('token') as string;
    this.user = JSON.parse(sessionStorage.getItem('user') as string) as any;
  }
  
  public authenticate(authRequest: AuthRequest) {
    return this.http.post<AuthResponse>(this.API_URL, {
      login: authRequest.login,
      password: authRequest.password
    }).pipe(
      tap(resp => {
        this.token = resp.token;
        this.user = resp;
        if (authRequest.rememberMe) {
          localStorage.setItem('token', this.token);
          localStorage.setItem('user', JSON.stringify(this.user));
        } else {
          sessionStorage.setItem('token', this.token);
          sessionStorage.setItem('user', JSON.stringify(this.user));
        }
      })
    );
  }
  
  public logout() {
    this.token = "";
    this.user = null;
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    localStorage.removeItem('panier');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('user');
    this.router.navigate(['/connexion']);
  }
  
}
