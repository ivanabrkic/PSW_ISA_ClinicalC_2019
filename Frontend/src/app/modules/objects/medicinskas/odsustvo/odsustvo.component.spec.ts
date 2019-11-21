import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdsustvoComponent } from './odsustvo.component';

describe('OdsustvoComponent', () => {
  let component: OdsustvoComponent;
  let fixture: ComponentFixture<OdsustvoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdsustvoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdsustvoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
