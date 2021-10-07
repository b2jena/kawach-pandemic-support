import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyEquipmentsComponent } from './verify-equipments.component';

describe('VerifyEquipmentsComponent', () => {
  let component: VerifyEquipmentsComponent;
  let fixture: ComponentFixture<VerifyEquipmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerifyEquipmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifyEquipmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
