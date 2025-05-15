import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalDetailBenevoleComponent } from './animal-detail-benevole.component';

describe('AnimalDetailBenevoleComponent', () => {
  let component: AnimalDetailBenevoleComponent;
  let fixture: ComponentFixture<AnimalDetailBenevoleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AnimalDetailBenevoleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnimalDetailBenevoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
