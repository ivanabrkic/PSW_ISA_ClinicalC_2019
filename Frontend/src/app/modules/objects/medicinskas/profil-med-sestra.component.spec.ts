import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilMedSestraComponent } from './profil-med-sestra.component';

describe('ProfilMedSestraComponent', () => {
  let component: ProfilMedSestraComponent;
  let fixture: ComponentFixture<ProfilMedSestraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfilMedSestraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilMedSestraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
