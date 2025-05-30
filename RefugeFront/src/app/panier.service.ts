import { Injectable } from '@angular/core';
import { Produit } from './produit';
import { ProduitService } from './produit.service';

@Injectable({
  providedIn: 'root'
})
export class PanierService {

  private _articles: Produit[] = [];

  constructor(private produitService: ProduitService) { 
    this.chargerPanier(); // On charge le panier au démarrage du service
  }

  get articles(): Produit[] {
    return this._articles;
  }

  chargerPanier() {
    const panier = localStorage.getItem('panier');
    if (panier) {
      this._articles = JSON.parse(panier);
    } else {
      this._articles = [];
    }
  }

  ajouter(produit: Produit) {
    this._articles.push(produit);
    localStorage.setItem('panier', JSON.stringify(this._articles)); // On le met à jour dans le LocalStorage
    this.produitService.decrementerStock(produit.id).subscribe({
      next: () => {
        console.log(`Stock du produit ${produit.libelle} diminué de 1`);
      },
      error: () => {
        console.log(`Erreur lors de la diminution du stock pour ${produit.libelle}`);
      }
    });
  }

  retirer(id: number) {
    const index = this._articles.findIndex(p => p.id === id);
    if (index !== -1) {
      this._articles.splice(index, 1);
      localStorage.setItem('panier', JSON.stringify(this._articles)); // On le met à jour dans le LocalStorage
      this.produitService.restaurerStock(id).subscribe({
        next: () => {
          console.log(`Stock du produit ${id} restauré`);
        },
        error: () => {  
            console.log(`Erreur lors de la restauration du stock pour ${id}`);
          }
      });
    }
  }
  get total(): number {
    const sum = this._articles.reduce((sum, article) => sum + article.prix, 0);
    return parseFloat(sum.toFixed(2));
  }

  viderPanier() {
    this._articles.forEach(article => {
      this.produitService.restaurerStock(article.id).subscribe({
        next: () => {
          console.log(`Stock du produit ${article.id} restauré`);
        },
        error: () => {
          console.error(`Erreur lors de la restauration du stock pour le produit ${article.id}`);
        }
      });
    });
  
   this._articles = []; 
   localStorage.removeItem('panier');
    console.log("Panier vidé !");
  }

  acheterPanier() {
    this._articles = [];
  }
}
