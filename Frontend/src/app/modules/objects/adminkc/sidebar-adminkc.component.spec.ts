import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarAdminkcComponent } from './sidebar-adminkc.component';

describe('SidebarAdminkcComponent', () => {
  let component: SidebarAdminkcComponent;
  let fixture: ComponentFixture<SidebarAdminkcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidebarAdminkcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarAdminkcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
