import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdmorComponent } from './odmor.component';

describe('OdmorComponent', () => {
  let component: OdmorComponent;
  let fixture: ComponentFixture<OdmorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdmorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdmorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
