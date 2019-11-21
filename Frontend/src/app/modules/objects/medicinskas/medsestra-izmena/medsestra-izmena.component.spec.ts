import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedsestraIzmenaComponent } from './medsestra-izmena.component';

describe('MedsestraIzmenaComponent', () => {
  let component: MedsestraIzmenaComponent;
  let fixture: ComponentFixture<MedsestraIzmenaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedsestraIzmenaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedsestraIzmenaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
