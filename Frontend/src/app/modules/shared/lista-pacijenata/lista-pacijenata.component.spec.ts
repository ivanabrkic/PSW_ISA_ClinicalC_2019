import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaPacijenataComponent } from './lista-pacijenata.component';

describe('ListaPacijenataComponent', () => {
  let component: ListaPacijenataComponent;
  let fixture: ComponentFixture<ListaPacijenataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaPacijenataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaPacijenataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
