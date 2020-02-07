import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogKreiranjeReceptaComponent } from './dijalog-kreiranje-recepta.component';

describe('DijalogKreiranjeReceptaComponent', () => {
  let component: DijalogKreiranjeReceptaComponent;
  let fixture: ComponentFixture<DijalogKreiranjeReceptaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DijalogKreiranjeReceptaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogKreiranjeReceptaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
