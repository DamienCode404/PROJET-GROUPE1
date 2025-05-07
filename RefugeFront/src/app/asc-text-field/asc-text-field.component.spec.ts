import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AscTextFieldComponent } from './asc-text-field.component';

describe('AscTextFieldComponent', () => {
  let component: AscTextFieldComponent;
  let fixture: ComponentFixture<AscTextFieldComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AscTextFieldComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AscTextFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
