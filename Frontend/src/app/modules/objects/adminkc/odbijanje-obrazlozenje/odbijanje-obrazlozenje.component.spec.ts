import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdbijanjeObrazlozenjeComponent } from './odbijanje-obrazlozenje.component';

describe('OdbijanjeObrazlozenjeComponent', () => {
  let component: OdbijanjeObrazlozenjeComponent;
  let fixture: ComponentFixture<OdbijanjeObrazlozenjeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdbijanjeObrazlozenjeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdbijanjeObrazlozenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
