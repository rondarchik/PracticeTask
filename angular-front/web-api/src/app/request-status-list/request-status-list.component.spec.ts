import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestStatusListComponent } from './request-status-list.component';

describe('RequestStatusListComponent', () => {
  let component: RequestStatusListComponent;
  let fixture: ComponentFixture<RequestStatusListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RequestStatusListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestStatusListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
