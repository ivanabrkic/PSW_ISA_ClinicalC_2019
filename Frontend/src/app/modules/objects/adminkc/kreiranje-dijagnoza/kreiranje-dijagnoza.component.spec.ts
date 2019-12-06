import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KreiranjeDijagnozaComponent } from './kreiranje-dijagnoza.component';

describe('KreiranjeDijagnozaComponent', () => {
  let component: KreiranjeDijagnozaComponent;
  let fixture: ComponentFixture<KreiranjeDijagnozaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KreiranjeDijagnozaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KreiranjeDijagnozaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
