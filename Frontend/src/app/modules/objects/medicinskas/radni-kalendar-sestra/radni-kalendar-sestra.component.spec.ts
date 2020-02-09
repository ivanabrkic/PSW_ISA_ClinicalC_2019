import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RadniKalendarSestraComponent } from './radni-kalendar-sestra.component';

describe('RadniKalendarSestraComponent', () => {
  let component: RadniKalendarSestraComponent;
  let fixture: ComponentFixture<RadniKalendarSestraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RadniKalendarSestraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RadniKalendarSestraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
