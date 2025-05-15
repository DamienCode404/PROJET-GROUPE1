import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Animal } from './animal';
import { AnimalService } from './animal.service';

@Component({
  selector: 'app-admin-animaux',
  standalone: false,
  templateUrl: './admin-animaux.component.html',
  styleUrl: './admin-animaux.component.css'
})
export class AdminAnimauxComponent implements OnInit, OnDestroy {
  animal$!: Observable<Animal[]>;
  subscriptions: any = [];

  constructor(private service: AnimalService) { }
  
  ngOnInit(): void {
    this.animal$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('delete');
  }

  public deleteAnimal(animal: Animal) {
    this.unsub('delete');

    this.subscriptions['delete'] = this.service.delete(animal).subscribe(() => this.service.refresh());
  }

  private unsub(name: string) {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }

}
