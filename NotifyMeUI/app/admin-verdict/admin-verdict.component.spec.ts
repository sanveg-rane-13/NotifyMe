import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminVerdictComponent } from './admin-verdict.component';

describe('AdminVerdictComponent', () => {
  let component: AdminVerdictComponent;
  let fixture: ComponentFixture<AdminVerdictComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminVerdictComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminVerdictComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
