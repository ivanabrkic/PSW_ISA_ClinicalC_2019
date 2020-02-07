import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogUnosDijagnozaComponent } from './dijalog-unos-dijagnoza.component';

describe('DijalogUnosDijagnozaComponent', () => {
  let component: DijalogUnosDijagnozaComponent;
  let fixture: ComponentFixture<DijalogUnosDijagnozaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DijalogUnosDijagnozaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogUnosDijagnozaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
