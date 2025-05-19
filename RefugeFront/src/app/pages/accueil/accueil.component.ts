import { Component, OnInit } from '@angular/core';
import { AnimalService } from '../admin-animaux/animal.service';
import { SearchBarService } from '../../search-bar/search-bar.service';
import { Animal } from '../admin-animaux/animal';
import { combineLatest, Observable, of } from 'rxjs';
import { map, startWith, switchMap } from 'rxjs/operators';
import { AuthService } from '../../auth.service';
import { Utilisateurs } from '../admin-utilisateurs/utilisateurs';
import { UtilisateursService } from '../admin-utilisateurs/utilisateurs.service';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  standalone: false,
  styleUrls: ['./accueil.component.css']
})

export class AccueilComponent implements OnInit {
  animaux$!: Observable<Animal[]>;
  recommandations$!: Observable<Animal[]>;
  animauxDisponibles$!: Observable<Animal[]>;
  animauxReserves$!: Observable<Animal[]>;
  animauxAdoptes$!: Observable<Animal[]>;
  private _role: string | null = null;
  private _id!: number;
  tagClient: string | null = null;
  selectedView: string = 'recommandations'; // Default view

  constructor(
    private animalService: AnimalService,
    private searchService: SearchBarService,
    private authService: AuthService,
    private utilisateursService: UtilisateursService
  ) {
    if (this.authService.user != null) {
      this._id = authService.user.idUser;
      this._role = this.authService.user.roleUser
    }
  }

  public get role() {
    return this.authService.user?.roleUser;
  }

  public set role(value: string | null) {
    this._role = value;
  }

  ngOnInit(): void {
    if (this.authService.user != null && this.authService.user.roleUser === "CLIENT") {
      this.utilisateursService.findById(this.authService.user.idUser).subscribe(client => {
        this.tagClient = client.tag;
        this.filterRecommendations(client);
      });
    } else {
      this.selectedView = 'disponibles';
    }

    // Fetch all animals and filter them based on status, search, and age filter
    this.animaux$ = this.filterAnimauxBySearchAndAge();
    this.animauxDisponibles$ = this.filterAnimauxByStatusAndSearchAndAge('Disponible');
    this.animauxReserves$ = this.filterAnimauxByStatusAndSearchAndAge('Reserve');
    this.animauxAdoptes$ = this.filterAnimauxByStatusAndSearchAndAge('Adopte');
  }

  private filterRecommendations(client: Utilisateurs): void {
    if (this.authService.user.roleUser === 'CLIENT' && client.tag) {
      this.recommandations$ = this.animalService.findAll().pipe(
        map(animaux =>
          animaux.filter(animal =>
            animal.tag?.toLowerCase() === client.tag?.toLowerCase() &&
            animal.statut === 'Disponible'
          )
        )
      );
    } else {
      this.recommandations$ = of([]); // Default empty observable if no recommendations
    }
  }

  private filterAnimauxBySearchAndAge(): Observable<Animal[]> {
    return combineLatest([
      this.animalService.findAll(),
      this.searchService.search$.pipe(
        startWith({ search: '', ageFilter: '' })
      )
    ]).pipe(
      map(([animaux, { search, ageFilter }]) => {
        const term = (search || '').trim().toLowerCase();
        return animaux.filter(animal => {
          const matchText = !term ||
            animal.nom.toLowerCase().includes(term) ||
            animal.race.toLowerCase().includes(term);

          const age = this.calculateAge(animal.naissance);
          const matchAge = this.matchAge(age, ageFilter);

          return matchText && matchAge;
        });
      })
    );
  }

  private filterAnimauxByStatusAndSearchAndAge(status: string): Observable<Animal[]> {
    return combineLatest([
      this.animalService.findAll(),
      this.searchService.search$.pipe(
        startWith({ search: '', ageFilter: '' })
      )
    ]).pipe(
      map(([animaux, { search, ageFilter }]) => {
        const term = (search || '').trim().toLowerCase();
        return animaux.filter(animal => {
          const matchText = !term ||
            animal.nom.toLowerCase().includes(term) ||
            animal.race.toLowerCase().includes(term);

          const age = this.calculateAge(animal.naissance);
          const matchAge = this.matchAge(age, ageFilter);

          const matchStatus = animal.statut === status ||
            (status === 'Disponible' && animal.statut === 'PremierContact');

          return matchText && matchAge && matchStatus;
        });
      })
    );
  }

  // Calcule l'âge de l'animal en années complètes.
  // @param birthDateString date de naissance de l'animal
  private calculateAge(birthDateString: string): number {
    const birthDate = new Date(birthDateString);
    const today = new Date();
    let age = today.getFullYear() - birthDate.getFullYear();

    // Ajustement si l'anniversaire n'est pas encore passé cette année
    const monthDiff = today.getMonth() - birthDate.getMonth();
    const dayDiff = today.getDate() - birthDate.getDate();
    if (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)) {
      age--;
    }
    return age;
  }

  // @param age âge de l'animal
  // @param filter valeur du filtre d'âge sélectionné
  private matchAge(age: number, filter: string): boolean {
    switch (filter) {
      case '0-1':
        return age < 1;
      case '1-3':
        return age >= 1 && age <= 3;
      case '3-5':
        return age > 3 && age <= 5;
      case '5+':
        return age > 5;
      default:
        return true; // accepter tous les âges
    }
  }
}
