import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistracijaKlinikeComponent } from './registracija-klinike.component';

describe('RegistracijaKlinikeComponent', () => {
  let component: RegistracijaKlinikeComponent;
  let fixture: ComponentFixture<RegistracijaKlinikeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistracijaKlinikeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistracijaKlinikeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
