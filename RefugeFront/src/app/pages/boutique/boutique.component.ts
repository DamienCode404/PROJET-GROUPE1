import { Component, OnInit } from '@angular/core';
import { combineLatest, map, Observable, startWith } from 'rxjs';
import { Produit } from '../../produit';
import { ProduitService } from '../../produit.service';
import { PanierService } from '../../panier.service';
import { SearchBarBoutiqueService } from '../../search-bar-boutique/search-bar-boutique.service';

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

  constructor(private service: ProduitService, 
    private panierService: PanierService,
    private searchBarBoutiqueService: SearchBarBoutiqueService
  ) {}

  ngOnInit(): void {
    // Combine la liste des produit et la valeur du formulaire de recherche
    this.produits$ = combineLatest([
      this.service.findAll(), // Récupère tous les produits depuis l'API
      this.searchBarBoutiqueService.search$.pipe(
        startWith({ search: ''}) // Au début, aucun filtre appliqué
      )
    ]).pipe(
      map(([produits, { search}]) => {
        const term = (search || '').trim().toLowerCase(); // Nettoie le texte recherché

        // filtre le texte + l'âge 
        return produits.filter(produit => {
          const matchText = !term ||
            produit.libelle.toLowerCase().includes(term) ||
            produit.description.toLowerCase().includes(term);

          return matchText; // Retourne seulement le produit passe le filtre
        });
      })
    );
  }

  ajouterAuPanier(produit: Produit): void {
    this.panierService.ajouter(produit);
    this.produitAjoute = true;
    setTimeout(() => {
      this.produitAjoute = false;  
    }, 3000);
  }
}   
