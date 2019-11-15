import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarMedSestraComponent } from './sidebar-med-sestra.component';

describe('SidebarMedSestraComponent', () => {
  let component: SidebarMedSestraComponent;
  let fixture: ComponentFixture<SidebarMedSestraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidebarMedSestraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarMedSestraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
