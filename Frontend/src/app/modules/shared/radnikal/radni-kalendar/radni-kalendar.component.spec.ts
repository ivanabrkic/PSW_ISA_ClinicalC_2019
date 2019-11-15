import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RadniKalendarComponent } from './radni-kalendar.component';

describe('RadniKalendarComponent', () => {
  let component: RadniKalendarComponent;
  let fixture: ComponentFixture<RadniKalendarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RadniKalendarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RadniKalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
