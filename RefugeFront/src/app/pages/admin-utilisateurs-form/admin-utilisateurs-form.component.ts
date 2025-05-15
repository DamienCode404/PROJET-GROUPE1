import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Utilisateurs } from '../admin-utilisateurs/utilisateurs';
import { UtilisateursService } from '../admin-utilisateurs/utilisateurs.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-utilisateurs-form',
  standalone: false,
  templateUrl: './admin-utilisateurs-form.component.html',
  styleUrl: './admin-utilisateurs-form.component.css'
})
export class AdminUtilisateursFormComponent implements OnInit, OnDestroy {
  utilisateursForm!: FormGroup;
  afficherForm: boolean = false;
  editingUtilisateurs!: Utilisateurs | null;
  subscriptions: any = [];

  TypeUsers: string[] = [
    'ADMIN',
    'CLIENT',
    'WORKER'
  ];
  


  // Liste de tous les tags disponibles
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
    private route: ActivatedRoute,
    private router: Router,
    private service: UtilisateursService,
    private formBuilder: FormBuilder) {}
    
    ngOnInit(): void {
      this.route.params.subscribe(params => {
        
        const id = params['id'];
        
        this.utilisateursForm = this.formBuilder.group({
          utilisateurType: ['', Validators.required],
          login: ['', Validators.required],
          password: ['', Validators.required],
          lastName: ['', Validators.required],
          firstName: ['', Validators.required],
          email: ['', [Validators.required, Validators.email]],
          phoneNumber: ['', Validators.required],
          imageBase64: [''],
          tag: ['']
        });
        
        if (id==0) {return}
        
        this.service.findById(id).subscribe({
          next: user => {
            this.editingUtilisateurs = user;
            this.utilisateursForm.get('utilisateurType')?.setValue(user.utilisateurType);
            this.utilisateursForm.get('login')?.setValue(user.login);
            this.utilisateursForm.get('password')?.setValue(user.password);
            this.utilisateursForm.get('lastName')?.setValue(user.lastName);
            this.utilisateursForm.get('firstName')?.setValue(user.firstName);
            this.utilisateursForm.get('email')?.setValue(user.email);
            this.utilisateursForm.get('phoneNumber')?.setValue(user.phoneNumber);
            this.utilisateursForm.get('imageBase64')?.setValue(user.imageBase64);
            this.utilisateursForm.get('tag')?.setValue(user.tag);
          },
          error: () => this.editingUtilisateurs = null
        });
      });
      
    }
    
    
    ngOnDestroy(): void {
      this.unsub('addOrEdit');
    }
    
    onImagePicked(event: Event) {
      const file = (event.target as HTMLInputElement)?.files?.[0]; 
      const reader = new FileReader();
      reader.readAsDataURL(file!);
      reader.onload = () => {
        this.utilisateursForm.patchValue({ imageBase64: reader.result});
      };
      
    }
    
    
    public addOrEditUtilisateurs(): void {
      this.unsub('addOrEdit');
      
      this.subscriptions['addOrEdit'] = this.service.save({
        id: this.editingUtilisateurs?.id,
        ...this.utilisateursForm.value
      }).subscribe({next:() => {this.service.refresh(); this.router.navigate(['/admin-utilisateurs'])}, error: (err) => {
        const message = err.error?.message;
        if (err.status === 400 && message?.includes('login')) {
          
        } else {
          alert('Login déja utilisé');
    }}});
      
      
    }
    
    private unsub(name: string) {
      if (this.subscriptions[name]) {
        this.subscriptions[name].unsubscribe();
        this.subscriptions[name] = null;
      }
    }
  }
