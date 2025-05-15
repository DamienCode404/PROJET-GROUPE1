import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBoutiqueFormComponent } from './admin-boutique-form.component';

describe('AdminBoutiqueFormComponent', () => {
  let component: AdminBoutiqueFormComponent;
  let fixture: ComponentFixture<AdminBoutiqueFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminBoutiqueFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminBoutiqueFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
