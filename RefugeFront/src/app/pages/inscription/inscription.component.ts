import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { InscriptionService } from './inscription.service';
import { Client } from './client';
import { AuthService } from '../../auth.service';
import { AuthRequest } from '../../auth-request';

@Component({
  selector: 'app-inscription',
  standalone: false,

  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {
  inscriptionForm!: FormGroup;

  tagsList: string[] = [
    'Calme',
    'Énergique',
    'Sportif',
    'Câlin',
    'Sociable',
    'Indépendant',
    'Joueur',
    'Protecteur',
    'Curieux',
    'Affectueux',
    'Timide',
    'Peureux',
    'Docile',
    'Intelligent',
    'Gourmand',
    'Obéissant',
    'AdapteAuxEnfants',
    'AdapteAuxChats',
    'AdapteAuxChiens',
    'BesoinEspace',
    'CompatibleAppartement',
    'LOF'
  ];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private inscriptionService: InscriptionService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.inscriptionForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      numero: [''],
      voie: [''],
      ville: [''],
      cp: [''],
      tag: ['']
    });
  }

  inscrire(): void {
    if (this.inscriptionForm.valid) {
      const client: Client = this.inscriptionForm.value;

      this.inscriptionService.inscrire(client).subscribe({
        next: (res: Client) => {
          console.log('Inscription réussie', res);
          this.authService.authenticate(new AuthRequest(
              res.login,
              res.password,
              false
            )).subscribe({
              next: () => this.router.navigate(['/home'])
            });
        },
        error: (err: any) => {
          console.error(' Erreur lors de l\'inscription', err);
        }
      });
    }
  }
}
