import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Animal } from '../admin-animaux/animal';
import { AnimalService } from '../admin-animaux/animal.service';
import { AuthService } from '../../auth.service';

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

  constructor(private service: AnimalService, private authService: AuthService) {
    this._id = authService.user.idUser;
  }

  ngOnInit(): void {
    this.animal$ = this.service.findAll().pipe(
      map(animal$ => animal$.filter(animal => animal.idWorker === this._id))
    );
  }

  public get id()
  {
    return this._id;
  }

  public set id(value:number){
    this._id = value;
  }

}
