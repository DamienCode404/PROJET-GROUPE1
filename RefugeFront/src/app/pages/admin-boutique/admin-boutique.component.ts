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
  produits$!: Observable<Produit[]>;
  subscriptions: any = [];
  
  constructor(private service: ProduitService) { }
  

  
  
  ngOnInit(): void {
    this.produits$ = this.service.findAll();
  }
  
  
  ngOnDestroy(): void {
    this.unsub('delete');
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
