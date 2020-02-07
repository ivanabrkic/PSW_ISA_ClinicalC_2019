import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaPacijenataComponent } from './pretraga-pacijenata.component';

describe('PretragaPacijenataComponent', () => {
  let component: PretragaPacijenataComponent;
  let fixture: ComponentFixture<PretragaPacijenataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PretragaPacijenataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PretragaPacijenataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
