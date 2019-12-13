import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogOdbijanjeZahtevaComponent } from './dijalog-odbijanje-zahteva.component';

describe('DijalogOdbijanjeZahtevaComponent', () => {
  let component: DijalogOdbijanjeZahtevaComponent;
  let fixture: ComponentFixture<DijalogOdbijanjeZahtevaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DijalogOdbijanjeZahtevaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogOdbijanjeZahtevaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
