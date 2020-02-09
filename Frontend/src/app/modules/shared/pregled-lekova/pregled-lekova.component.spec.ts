import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledLekovaComponent } from './pregled-lekova.component';

describe('PregledLekovaComponent', () => {
  let component: PregledLekovaComponent;
  let fixture: ComponentFixture<PregledLekovaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PregledLekovaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PregledLekovaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
