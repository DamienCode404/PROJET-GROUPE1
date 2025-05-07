import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchBarService {
  private searchSubject = new BehaviorSubject<{search: string, ageFilter: string}>({search: '', ageFilter: ''});
  search$ = this.searchSubject.asObservable();

  updateSearch(formValue: {search: string, ageFilter: string}) {
    this.searchSubject.next(formValue);
  }
}