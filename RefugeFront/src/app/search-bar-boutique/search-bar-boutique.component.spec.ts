import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBarBoutiqueComponent } from './search-bar-boutique.component';

describe('SearchBarBoutiqueComponent', () => {
  let component: SearchBarBoutiqueComponent;
  let fixture: ComponentFixture<SearchBarBoutiqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SearchBarBoutiqueComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchBarBoutiqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
