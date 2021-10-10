import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseSosBedComponent } from './close-sos-bed.component';

describe('CloseSosBedComponent', () => {
  let component: CloseSosBedComponent;
  let fixture: ComponentFixture<CloseSosBedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CloseSosBedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CloseSosBedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
