import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { AnimalService } from '../admin-animaux/animal.service';
import { Animal } from '../admin-animaux/animal';

@Component({
  selector: 'app-animal-card',
  standalone: false,
  templateUrl: './animal-card.component.html',
  styleUrl: './animal-card.component.css'
})
export class AnimalCardComponent {
  @Input() animaux: Animal[] | null = null;
  @Input() routePrefix: string = 'animal';

  getPlaceholderImage(): string {
    const placeholders = [
      'placeholder--cat.png',
      'placeholder--dog.png',
      'placeholder.png'
    ];

    // Generate a random index to select a placeholder
    const randomIndex = Math.floor(Math.random() * placeholders.length);
    return placeholders[randomIndex];
  }
}
