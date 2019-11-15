import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazPacijenataTabelaComponent } from './prikaz-pacijenata-tabela.component';

describe('PrikazPacijenataTabelaComponent', () => {
  let component: PrikazPacijenataTabelaComponent;
  let fixture: ComponentFixture<PrikazPacijenataTabelaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazPacijenataTabelaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazPacijenataTabelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
