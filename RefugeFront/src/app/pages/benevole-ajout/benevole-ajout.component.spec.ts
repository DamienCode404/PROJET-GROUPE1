import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenevoleAjoutComponent } from './benevole-ajout.component';

describe('BenevoleAjoutComponent', () => {
  let component: BenevoleAjoutComponent;
  let fixture: ComponentFixture<BenevoleAjoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BenevoleAjoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BenevoleAjoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
