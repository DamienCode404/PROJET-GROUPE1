import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenevoleProfileComponent } from './benevole-profile.component';

describe('BenevoleProfileComponent', () => {
  let component: BenevoleProfileComponent;
  let fixture: ComponentFixture<BenevoleProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BenevoleProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BenevoleProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
