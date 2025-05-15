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
      
      if (id==0) {return}
      
      this.service.findById(id).subscribe({
        next: animal => {
          this.editingAnimal = animal;},
          error: () => this.editingAnimal = null
        });
      });

      this.animalForm = this.formBuilder.group({
        nom: ['', Validators.required],
        race: ['', Validators.required],
        naissance: ['', Validators.required],
        description: ['', Validators.required],
        statut: ['', Validators.required],
        imageBase64: ['']
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
