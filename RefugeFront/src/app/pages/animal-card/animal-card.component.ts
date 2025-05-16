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
}
