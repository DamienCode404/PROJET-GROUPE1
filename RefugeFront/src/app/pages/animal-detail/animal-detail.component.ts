import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Animal } from '../admin-animaux/animal';
import { AnimalService } from '../admin-animaux/animal.service';
import { AuthService } from '../../auth.service';

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
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      this.service.findById(id).subscribe({
        next: animal => this._animal = animal,
        error: () => this._animal = new Animal(0,'_nom: string', '_race: string', '_naissance: string', '_description: string', '_statut: string', 0, '_imageBase64: string','_tag: string|null')
      });
    });
  }

  public get animal(): Animal {
    return this._animal;
  }

  public premierContact() {
    this.animal.statut = "PremierContact";
    this.animal.idContact = this.authService.user.idUser;
    this.service.save(this.animal).subscribe(() => this.service.refresh());
  }
}
