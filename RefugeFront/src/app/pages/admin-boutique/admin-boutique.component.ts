import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Produit } from '../../produit';
import { ProduitService } from '../../produit.service';

@Component({
  selector: 'app-admin-boutique',
  standalone: false,
  templateUrl: './admin-boutique.component.html',
  styleUrl: './admin-boutique.component.css'
})
export class AdminBoutiqueComponent implements OnInit, OnDestroy  {
  produitForm!: FormGroup;
  produits$!: Observable<Produit[]>;
  afficherForm: boolean = false;
  editingProduit!: Produit | null;
  subscriptions: any = [];

  constructor(private service: ProduitService, private formBuilder: FormBuilder) { }


  onImagePicked(event: Event) {
    const file = (event.target as HTMLInputElement)?.files?.[0]; 
    const reader = new FileReader();
    reader.readAsDataURL(file!);
    reader.onload = () => {
        this.produitForm.patchValue({ imageBase64: reader.result});
    };
  }
  

  ngOnInit(): void {
    this.produitForm = this.formBuilder.group({
      libelle: ['', Validators.required],
      description: ['', Validators.required],
      prix: ['', Validators.required],
      stock: ['', Validators.required],
      imageBase64: [''] 
    });

    this.produits$ = this.service.findAll();
  }

  
  ngOnDestroy(): void {
    this.unsub('addOrEdit');
    this.unsub('delete');
  }

  public addOrEditProduit() {
    this.unsub('addOrEdit');
  
    this.subscriptions['addOrEdit'] = this.service.save({
      id: this.editingProduit?.id,
      ...this.produitForm.value
    }).subscribe(() => {
      // recharge les données 
      this.produits$ = this.service.findAll();
  
      this.editingProduit = null;
      this.produitForm.get('libelle')?.setValue('');
      this.produitForm.get('description')?.setValue('');
      this.produitForm.get('prix')?.setValue('');
      this.produitForm.get('stock')?.setValue('');
      this.afficherForm = false;
    });
  }

  public editProduit(produit: Produit) {

    this.produitForm.get('libelle')?.setValue(produit.libelle);
    this.produitForm.get('description')?.setValue(produit.description);
    this.produitForm.get('prix')?.setValue(produit.prix);
    this.produitForm.get('stock')?.setValue(produit.stock);

    this.editingProduit = produit;
    
    this.afficherForm = true;
  }

  public deleteProduit(produit: Produit) {
    this.unsub('delete');

    this.subscriptions['delete'] = this.service.delete(produit).subscribe(() => this.service.refresh());
  }

  private unsub(name: string) {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
