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
  
  constructor(private service: AnimalService, private authService: AuthService) {
    this._id = authService.user.idUser;
  }
  
  ngOnInit(): void {
    this.service.findAll().subscribe(animals => {
      const filteredAnimals = animals.filter(animal => animal.idWorker === this._id);
      
      this.animal$ = of(filteredAnimals);
      this.animalContactes$ = of(
        filteredAnimals.filter(animal => animal.statut === "PremierContact")
      );
    });
    
  };
  
  
  public get id()
  {
    return this._id;
  }
  
  public set id(value:number){
    this._id = value;
  }
  
}
