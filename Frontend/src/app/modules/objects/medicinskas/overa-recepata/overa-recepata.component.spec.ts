import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OveraRecepataComponent } from './overa-recepata.component';

describe('OveraRecepataComponent', () => {
  let component: OveraRecepataComponent;
  let fixture: ComponentFixture<OveraRecepataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OveraRecepataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OveraRecepataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
