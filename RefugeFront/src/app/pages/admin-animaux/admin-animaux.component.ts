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
  animalForm!: FormGroup;
  animal$!: Observable<Animal[]>;
  afficherForm: boolean = false;
  editingAnimal!: Animal | null;
  subscriptions: any = [];

  constructor(private service: AnimalService, private formBuilder: FormBuilder) { }
  
  onImagePicked(event: Event) {
    const file = (event.target as HTMLInputElement)?.files?.[0]; 
    const reader = new FileReader();
    reader.readAsDataURL(file!);
    reader.onload = () => {
        this.animalForm.patchValue({ imageBase64: reader.result});
    };
  }
  
  ngOnInit(): void {
    this.animalForm = this.formBuilder.group({
      nom: ['', Validators.required],
      race: ['', Validators.required],
      naissance: ['', Validators.required],
      description: ['', Validators.required],
      statut: ['', Validators.required],
      imageBase64: ['', Validators.required]
    });
    
    this.animal$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('addOrEdit');
    this.unsub('delete');
  }

  public addOrEditAnimal() {
    this.unsub('addOrEdit');

    this.subscriptions['addOrEdit'] = this.service.save({
      id: this.editingAnimal?.id,
      idWorker: this.editingAnimal?.idWorker,
      ...this.animalForm.value
    }).subscribe(() => this.service.refresh());

    this.editingAnimal = null;
    this.animalForm.get('nom')?.setValue("");
    this.animalForm.get('race')?.setValue("");
    this.animalForm.get('naissance')?.setValue("");
    this.animalForm.get('description')?.setValue("");
    this.animalForm.get('statut')?.setValue("");

    this.afficherForm = false;
  }

  public editAnimal(animal: Animal) {
    this.animalForm.get('nom')?.setValue(animal.nom);
    this.animalForm.get('race')?.setValue(animal.race);
    this.animalForm.get('naissance')?.setValue(animal.naissance);
    this.animalForm.get('description')?.setValue(animal.description);
    this.animalForm.get('statut')?.setValue(animal.statut);
    this.editingAnimal = animal;

    this.afficherForm = true;
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
