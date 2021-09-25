import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LatestInformationComponent } from './latest-information.component';

describe('LatestInformationComponent', () => {
  let component: LatestInformationComponent;
  let fixture: ComponentFixture<LatestInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LatestInformationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LatestInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
