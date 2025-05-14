import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchBarBoutiqueService {

  private searchSubject = new BehaviorSubject<{search: string, priceFilter: number}>({search: '', priceFilter: 0});
  search$ = this.searchSubject.asObservable();

  updateSearch(formValue: {search: string, priceFilter: number}) {
    this.searchSubject.next(formValue);
  }
}
