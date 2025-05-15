import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Produit } from '../../produit';
import { ProduitService } from '../../produit.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-boutique-form',
  standalone: false,
  templateUrl: './admin-boutique-form.component.html',
  styleUrl: './admin-boutique-form.component.css'
})
export class AdminBoutiqueFormComponent implements OnInit, OnDestroy {
  produitForm!: FormGroup;
  editingProduit!: Produit | null;
  subscriptions: any = [];
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: ProduitService,
    private formBuilder: FormBuilder) { }
    
    ngOnInit(): void {
      this.route.params.subscribe(params => {
        
        const id = params['id'];
        
        this.produitForm = this.formBuilder.group({
          libelle: ['', Validators.required],
          description: ['', Validators.required],
          prix: ['', Validators.required],
          stock: ['', Validators.required],
          imageBase64: ['']
        });
        
        if (id==0) {return}
        
        this.service.findById(id).subscribe({
          next: produit => {
            this.editingProduit = produit;
            this.produitForm.get('libelle')?.setValue(produit.libelle);
            this.produitForm.get('description')?.setValue(produit.description);
            this.produitForm.get('prix')?.setValue(produit.prix);
            this.produitForm.get('stock')?.setValue(produit.stock);
            this.produitForm.get('imageBase64')?.setValue(produit.imageBase64);
            error: () => this.editingProduit = null
          }});
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
          this.produitForm.patchValue({ imageBase64: reader.result});
        };
      }
      
      public addOrEditProduit() {
        this.unsub('addOrEdit');

        this.subscriptions['addOrEdit'] = this.service.save({
        id: this.editingProduit?.id,
        ...this.produitForm.value
      }).subscribe(() => {this.service.refresh(); this.router.navigate(['/admin-boutique'])});

      }
      
      
      private unsub(name: string) {
        if (this.subscriptions[name]) {
          this.subscriptions[name].unsubscribe();
          this.subscriptions[name] = null;
        }
      }
      
    }
