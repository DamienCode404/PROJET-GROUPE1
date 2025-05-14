import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SearchBarService } from '../search-bar/search-bar.service';
import { debounceTime, distinctUntilChanged } from 'rxjs';
import { SearchBarBoutiqueService } from './search-bar-boutique.service';

@Component({
  selector: 'app-search-bar-boutique',
  standalone: false,
  templateUrl: './search-bar-boutique.component.html',
  styleUrl: './search-bar-boutique.component.css'
})
export class SearchBarBoutiqueComponent implements OnInit {

  searchForm!: FormGroup;

  constructor(private fb: FormBuilder, private searchService: SearchBarBoutiqueService) {}

    ngOnInit(): void {
      this.searchForm = this.fb.group({
        search: [''],
        priceFilter: ['']
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
