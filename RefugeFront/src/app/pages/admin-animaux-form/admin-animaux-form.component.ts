import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Animal } from '../admin-animaux/animal';
import { AnimalService } from '../admin-animaux/animal.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-animaux-form',
  standalone: false,
  templateUrl: './admin-animaux-form.component.html',
  styleUrl: './admin-animaux-form.component.css'
})
export class AdminAnimauxFormComponent implements OnInit, OnDestroy {
  animalForm!: FormGroup;
  editingAnimal!: Animal|null;
  subscriptions: any = [];
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: AnimalService,
    private formBuilder: FormBuilder
  ) { }
  
  ngOnInit() {
    this.route.params.subscribe(params => {
      
      const id = params['id'];

      this.animalForm = this.formBuilder.group({
        nom: ['', Validators.required],
        race: ['', Validators.required],
        naissance: ['', Validators.required],
        description: ['', Validators.required],
        statut: ['', Validators.required],
        imageBase64: ['']
      });

      if (id==0) {return}
      
      this.service.findById(id).subscribe({
        next: animal => {
          this.editingAnimal = animal;
          this.animalForm.get('nom')?.setValue(animal.nom);
          this.animalForm.get('description')?.setValue(animal.description);
          this.animalForm.get('race')?.setValue(animal.race);
          this.animalForm.get('naissance')?.setValue(animal.naissance);
          this.animalForm.get('imageBase64')?.setValue(animal.imageBase64);
          this.animalForm.get('statut')?.setValue(animal.statut);},
          error: () => this.editingAnimal = null
        });
      });
      
    }
    
    ngOnDestroy(): void {
      this.unsub('addOrEdit');
    }
    
    onImagePicked(event: Event) {
      const file = (event.target as HTMLInputElement)?.files?.[0]; 
      const reader = new FileReader();
      reader.readAsDataURL(file!);
      reader.onload = () => {
        this.animalForm.patchValue({ imageBase64: reader.result});
      };
    }
    
    public addOrEditAnimal() {
      this.unsub('addOrEdit');
      
      this.subscriptions['addOrEdit'] = this.service.save({
        id: this.editingAnimal?.id,
        idWorker: this.editingAnimal?.idWorker,
        ...this.animalForm.value
      }).subscribe(() => {this.service.refresh(); this.router.navigate(['/admin-animaux'])});
      
      
    }
    
    private unsub(name: string) {
      if (this.subscriptions[name]) {
        this.subscriptions[name].unsubscribe();
        this.subscriptions[name] = null;
      }
    }
    
  }
