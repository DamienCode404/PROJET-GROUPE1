import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Animal } from '../admin-animaux/animal';
import { AnimalService } from '../admin-animaux/animal.service';

@Component({
  selector: 'app-animal-detail',
  standalone: false,
  templateUrl: './animal-detail.component.html',
  styleUrl: './animal-detail.component.css'
})

export class AnimalDetailComponent {
  private _animal!: Animal;

  constructor(
    private route: ActivatedRoute,
    private service: AnimalService,
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      this.service.findById(id).subscribe({
        next: animal => this._animal = animal,
        error: () => this._animal = new Animal(0,'_nom: string', '_race: string', '_naissance: string', '_description: string', '_statut: string', 0, '_imageBase64: string')
      });
    });
  }

  public get animal(): Animal {
    return this._animal;
  }

  public premierContact() {
    this.animal.statut = "Reserve"
    this.service.save(this.animal).subscribe(() => this.service.refresh());
  }
}
