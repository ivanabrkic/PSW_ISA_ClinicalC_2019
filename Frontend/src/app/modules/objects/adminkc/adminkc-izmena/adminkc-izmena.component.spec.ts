import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminkcIzmenaComponent } from './adminkc-izmena.component';

describe('AdminkcIzmenaComponent', () => {
  let component: AdminkcIzmenaComponent;
  let fixture: ComponentFixture<AdminkcIzmenaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminkcIzmenaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminkcIzmenaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
