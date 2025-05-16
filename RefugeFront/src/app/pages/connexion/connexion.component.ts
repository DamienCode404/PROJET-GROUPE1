import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { AuthRequest } from '../../auth-request';
import { AuthResponse } from '../../auth-response';

@Component({
  selector: 'app-connexion',
  standalone: false,
  templateUrl: './connexion.component.html',
  styleUrl: './connexion.component.css'
})
export class ConnexionComponent implements OnInit {
  public authForm!: FormGroup;
  public loginCtrl!: FormControl;
  public passwordCtrl!: FormControl;
  
  constructor(private service: AuthService, private router: Router, private formBuilder: FormBuilder) { }
  
  ngOnInit(): void {
    if (this.service.user)
      {
      this.router.navigate([ '/home' ]);
    }
    
    this.loginCtrl = this.formBuilder.control('admin', Validators.required);
    this.passwordCtrl = this.formBuilder.control('123456', [ Validators.required, Validators.minLength(6) ]);
    const rememberCtrl = this.formBuilder.control(false);
    
    // this.authForm = this.formBuilder.group({
    //   login: this.formBuilder.control('Valeur par défaut', Validators.required),
    //   password: this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ])
    // });
    
    this.authForm = this.formBuilder.group({
      login: this.loginCtrl,
      password: this.passwordCtrl,
      rememberMe: rememberCtrl
    });
  }
  
  public async authenticate() {
    this.service.authenticate(new AuthRequest(
    this.authForm.value.login,
    this.authForm.value.password,
    this.authForm.value.rememberMe
  )).subscribe({
    next: () => this.router.navigate(['/home']),
    error: err => console.error('Erreur de connexion', err)
  });
  }
}
