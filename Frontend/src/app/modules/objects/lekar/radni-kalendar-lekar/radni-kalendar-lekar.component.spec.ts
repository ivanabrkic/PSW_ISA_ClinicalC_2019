import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RadniKalendarLekarComponent } from './radni-kalendar-lekar.component';

describe('RadniKalendarLekarComponent', () => {
  let component: RadniKalendarLekarComponent;
  let fixture: ComponentFixture<RadniKalendarLekarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RadniKalendarLekarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RadniKalendarLekarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
