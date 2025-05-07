import { Component, OnInit } from '@angular/core';
import { AnimalService } from '../admin-animaux/animal.service';
import { Observable } from 'rxjs';
import { Animal } from '../admin-animaux/animal';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-benevole-ajout',
  standalone: false,
  templateUrl: './benevole-ajout.component.html',
  styleUrl: './benevole-ajout.component.css'
})
export class BenevoleAjoutComponent implements OnInit{

  // ID DU WORKER QUI EST CONNECTE
  private _id:number;
  
  animal$!: Observable<Animal[]>;

  constructor(private service: AnimalService, private authService: AuthService) {
    this._id = authService.user.idUser;
  }

  ngOnInit(): void {
    this.animal$ = this.service.findAll();
  }

  public get id()
  {
    return this._id;
  }

  public set id(value:number){
    this._id = value;
  }

  public retirer(animal:Animal)
  {
    animal.idWorker = null;
    this.service.save(animal).subscribe(() => this.service.refresh());;
  }

  public ajouter(animal:Animal)
  {
    animal.idWorker = this._id;
    this.service.save(animal).subscribe(() => this.service.refresh());;
  }

}
