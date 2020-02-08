import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdsustvoDijalogComponent } from './odsustvo-dijalog.component';

describe('OdsustvoDijalogComponent', () => {
  let component: OdsustvoDijalogComponent;
  let fixture: ComponentFixture<OdsustvoDijalogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdsustvoDijalogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdsustvoDijalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
