import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaIzvestajaComponent } from './izmena-izvestaja.component';

describe('IzmenaIzvestajaComponent', () => {
  let component: IzmenaIzvestajaComponent;
  let fixture: ComponentFixture<IzmenaIzvestajaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmenaIzvestajaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmenaIzvestajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
