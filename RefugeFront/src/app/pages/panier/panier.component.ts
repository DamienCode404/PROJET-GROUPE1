import { Component } from '@angular/core';
import { PanierService } from '../../panier.service';

@Component({
  selector: 'app-panier',
  standalone: false,
  templateUrl: './panier.component.html',
  styleUrl: './panier.component.css'
})
export class PanierComponent {
  constructor(public panierService: PanierService) {}
  acheterPanier(): void {
    this.panierService.acheterPanier();  
    console.log("Le panier a été vidé après l'achat.");
  }
}
