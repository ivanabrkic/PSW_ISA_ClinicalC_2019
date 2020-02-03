import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PredefinisaniTerminiComponent } from './predefinisani-termini.component';

describe('PredefinisaniTerminiComponent', () => {
  let component: PredefinisaniTerminiComponent;
  let fixture: ComponentFixture<PredefinisaniTerminiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PredefinisaniTerminiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PredefinisaniTerminiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
