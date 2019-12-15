import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KreiranjeLekovaComponent } from './kreiranje-lekova.component';

describe('KreiranjeLekovaComponent', () => {
  let component: KreiranjeLekovaComponent;
  let fixture: ComponentFixture<KreiranjeLekovaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KreiranjeLekovaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KreiranjeLekovaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
