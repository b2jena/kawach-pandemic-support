import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyBedComponent } from './verify-bed.component';

describe('VerifyBedComponent', () => {
  let component: VerifyBedComponent;
  let fixture: ComponentFixture<VerifyBedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerifyBedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifyBedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
