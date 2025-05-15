import { Component, OnInit } from '@angular/core';
import { Observable, map, of, switchMap, take, tap, throwError } from 'rxjs';
import { Animal } from '../admin-animaux/animal';
import { AnimalService } from '../admin-animaux/animal.service';
import { AuthService } from '../../auth.service';
import { UtilisateursService } from '../admin-utilisateurs/utilisateurs.service';
import { Utilisateurs } from '../admin-utilisateurs/utilisateurs';

@Component({
  selector: 'app-benevole-profile',
  standalone: false,
  templateUrl: './benevole-profile.component.html',
  styleUrl: './benevole-profile.component.css'
})
export class BenevoleProfileComponent implements OnInit {
  
  // ID DU WORKER QUI EST CONNECTE
  private _id:number;
  
  animal$!: Observable<Animal[]>;
  animalContactes$!: Observable<Animal[]>;
  contacts$!: Observable<Utilisateurs[]>;
  
  constructor(private service: AnimalService, private authService: AuthService, private userService: UtilisateursService) {
    this._id = authService.user.idUser;
  }
  
  ngOnInit(): void {
    this.animal$ = this.service.findAll().pipe(
      map(animal$ => animal$.filter(animal => animal.idWorker === this._id))
    );
    
    this.animalContactes$ = this.service.findAll().pipe(
      map(animalContactes$ => animalContactes$.filter(animal => (animal.idWorker === this._id) && animal.statut === "PremierContact"))
    );
    
    this.contacts$ = this.userService.findAll();

  };
  
  
  public get id()
  {
    return this._id;
  }
  
  public set id(value:number){
    this._id = value;
  }
  
}
