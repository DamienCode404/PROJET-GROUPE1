import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAnimauxFormComponent } from './admin-animaux-form.component';

describe('AdminAnimauxFormComponent', () => {
  let component: AdminAnimauxFormComponent;
  let fixture: ComponentFixture<AdminAnimauxFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAnimauxFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAnimauxFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
