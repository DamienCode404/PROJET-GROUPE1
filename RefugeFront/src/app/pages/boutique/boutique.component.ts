import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Produit } from '../../produit';
import { ProduitService } from '../../produit.service';
import { PanierService } from '../../panier.service';

@Component({
  selector: 'app-boutique',
  standalone: false,
  templateUrl: './boutique.component.html',
  styleUrl: './boutique.component.css'
})
export class BoutiqueComponent implements OnInit {
  produits$!: Observable<Produit[]>;
  subscriptions: any = [];
  produitAjoute: boolean = false;

  constructor(private service: ProduitService, private panierService: PanierService
  ) { }

  ngOnInit(): void {
    this.produits$ = this.service.findAll();
  }

  ajouterAuPanier(produit: Produit): void {
    this.panierService.ajouter(produit);
    this.produitAjoute = true;
    setTimeout(() => {
      this.produitAjoute = false;  
    }, 3000);
  }
}   
