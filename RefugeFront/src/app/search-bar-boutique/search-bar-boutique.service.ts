import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchBarBoutiqueService {

  private searchSubject = new BehaviorSubject<{search: string}>({search: ''});
  search$ = this.searchSubject.asObservable();

  updateSearch(formValue: {search: string}) {
    this.searchSubject.next(formValue);
  }
}
