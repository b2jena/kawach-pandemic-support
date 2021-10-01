import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseSosComponent } from './close-sos.component';

describe('CloseSosComponent', () => {
  let component: CloseSosComponent;
  let fixture: ComponentFixture<CloseSosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CloseSosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CloseSosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
