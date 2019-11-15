import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminkcComponent } from './adminkc.component';

describe('AdminkcComponent', () => {
  let component: AdminkcComponent;
  let fixture: ComponentFixture<AdminkcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminkcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminkcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
