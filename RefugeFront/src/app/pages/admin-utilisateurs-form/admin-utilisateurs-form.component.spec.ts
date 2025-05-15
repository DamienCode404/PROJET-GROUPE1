import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUtilisateursFormComponent } from './admin-utilisateurs-form.component';

describe('AdminUtilisateursFormComponent', () => {
  let component: AdminUtilisateursFormComponent;
  let fixture: ComponentFixture<AdminUtilisateursFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminUtilisateursFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminUtilisateursFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
