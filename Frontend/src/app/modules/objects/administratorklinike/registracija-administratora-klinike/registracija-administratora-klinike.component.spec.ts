import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistracijaAdministratoraKlinikeComponent } from './registracija-administratora-klinike.component';

describe('RegistracijaAdministratoraKlinikeComponent', () => {
  let component: RegistracijaAdministratoraKlinikeComponent;
  let fixture: ComponentFixture<RegistracijaAdministratoraKlinikeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistracijaAdministratoraKlinikeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistracijaAdministratoraKlinikeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
