import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAnimauxComponent } from './admin-animaux.component';

describe('AdminAnimauxComponent', () => {
  let component: AdminAnimauxComponent;
  let fixture: ComponentFixture<AdminAnimauxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAnimauxComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAnimauxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
