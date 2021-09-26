import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListBedComponent } from './list-bed.component';

describe('ListBedComponent', () => {
  let component: ListBedComponent;
  let fixture: ComponentFixture<ListBedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListBedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListBedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
