import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Utilisateurs } from './utilisateurs';
import { UtilisateursService } from './utilisateurs.service';

@Component({
  selector: 'app-admin-utilisateurs',
  standalone: false,
  templateUrl: './admin-utilisateurs.component.html',
  styleUrl: './admin-utilisateurs.component.css'
})
export class AdminUtilisateursComponent {
  utilisateursForm!: FormGroup;
  utilisateurs$!: Observable<Utilisateurs[]>;
  afficherForm: boolean = false;
  editingUtilisateurs!: Utilisateurs | null;
  subscriptions: any = [];

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
  
  constructor(private service: UtilisateursService, private formBuilder: FormBuilder) {}
  
  onImagePicked(event: Event) {
    const file = (event.target as HTMLInputElement)?.files?.[0]; 
    const reader = new FileReader();
    reader.readAsDataURL(file!);
    reader.onload = () => {
        this.utilisateursForm.patchValue({ imageBase64: reader.result});
    };
   
  }
  

  ngOnInit(): void {
    this.utilisateursForm = this.formBuilder.group({
      utilisateurType: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      lastName: ['', Validators.required],
      firstName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required],
      imageBase64: [''],
      tag: ['', Validators.required]
    });

    this.utilisateurs$ = this.service.findAll();
    this.service.refresh();

  }

  ngOnDestroy(): void {
    this.unsub('addOrEdit');
    this.unsub('delete');
  }

  public addOrEditUtilisateurs(): void {
    this.unsub('addOrEdit');

    const utilisateurData = {
      id: this.editingUtilisateurs?.id,
      ...this.utilisateursForm.value
    };

    this.subscriptions['addOrEdit'] = this.service.save(utilisateurData).subscribe(() => {
      this.service.refresh();
      this.utilisateursForm.reset();
      this.editingUtilisateurs = null;
    });

    this.afficherForm = false;
  }

  public editUtilisateurs(utilisateur: Utilisateurs): void {
    this.utilisateursForm.patchValue({
      utilisateurType: utilisateur.utilisateurType,
      login: utilisateur.login,
      password: utilisateur.password,
      lastName: utilisateur.lastName,
      firstName: utilisateur.firstName,
      email: utilisateur.email,
      phoneNumber: utilisateur.phoneNumber,
      tag : utilisateur.tag
    });
    this.editingUtilisateurs = utilisateur;
    this.afficherForm = true;
  }

  public deleteUtilisateurs(utilisateurs: Utilisateurs) {
    this.unsub('delete');

    this.subscriptions['delete'] = this.service.delete(utilisateurs).subscribe(() => this.service.refresh());
  }

  private unsub(name: string) {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
