import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Animal } from '../admin-animaux/animal';
import { AnimalService } from '../admin-animaux/animal.service';
import { AuthService } from '../../auth.service';
import { UtilisateursService } from '../admin-utilisateurs/utilisateurs.service';
import { Utilisateurs } from '../admin-utilisateurs/utilisateurs';
@Component({
  selector: 'app-animal-detail-benevole',
  standalone: false,
  templateUrl: './animal-detail-benevole.component.html',
  styleUrl: './animal-detail-benevole.component.css'
})
export class AnimalDetailBenevoleComponent {
  private _animal!: Animal;
  private _contact!: Utilisateurs;
  
  constructor(
    private route: ActivatedRoute,
    private service: AnimalService,
    private authService: AuthService,
    private userService: UtilisateursService
  ) {}
  
  ngOnInit(): void {
  this.route.params.subscribe(params => {
    const id = params['id'];
    this.service.findById(id).subscribe({
      next: animal => {
        this._animal = animal;

        this.userService.findById(animal.idContact).subscribe({
          next: user => this._contact = user,
          error: () => this._contact = new Utilisateurs(0,"_utilisateurType: string","_login: string", "_password: string","_lastName: string","_firstName: string","_email: string","_phoneNumber: string","_imageBase64: string","_tag: string")
        });

      },
      error: () => this._animal = new Animal(0,'_nom: string', '_race: string', '_naissance: string', '_description: string', '_statut: string', 0, '_imageBase64: string')
    });
  });
}
  
  public get animal(): Animal {
    return this._animal;
  }
  
  public get contact(): Utilisateurs {
    return this._contact;
  }
  
  public changeStatutReserve() {
    this._animal.statut = "Reserve";
    this.service.save(this._animal).subscribe();
  }
  
}
