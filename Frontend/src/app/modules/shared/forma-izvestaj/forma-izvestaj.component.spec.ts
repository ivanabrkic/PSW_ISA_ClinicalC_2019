import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaIzvestajComponent } from './forma-izvestaj.component';

describe('FormaIzvestajComponent', () => {
  let component: FormaIzvestajComponent;
  let fixture: ComponentFixture<FormaIzvestajComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormaIzvestajComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaIzvestajComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
