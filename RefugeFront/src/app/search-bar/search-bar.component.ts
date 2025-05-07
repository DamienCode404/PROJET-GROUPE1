import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { SearchBarService } from './search-bar.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  standalone: false,
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
  searchForm!: FormGroup;

  constructor(private fb: FormBuilder, private searchService: SearchBarService) {}

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      search: [''],
      ageFilter: ['']
    });

    this.searchForm.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged()
    ).subscribe(formValue => {
      console.log('🔎 Mise à jour du formulaire', formValue);
      this.searchService.updateSearch(formValue);
    });
  }
}