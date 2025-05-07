import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { InscriptionService } from './inscription.service';
import { Client } from './client';

@Component({
  selector: 'app-inscription',
  standalone: false,

  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {
  inscriptionForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private inscriptionService: InscriptionService
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
      cp: ['']
    });
  }

  inscrire(): void {
    if (this.inscriptionForm.valid) {
      const client: Client = this.inscriptionForm.value;

      this.inscriptionService.inscrire(client).subscribe({
        next: (res: Client) => {
          console.log('Inscription réussie', res);
          this.router.navigate(['/connexion']);
        },
        error: (err: any) => {
          console.error(' Erreur lors de l\'inscription', err);
        }
      });
    }
  }
}
