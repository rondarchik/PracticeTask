import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditTypeListComponent } from './credit.type.list.component';

describe('CreditTypeListComponent', () => {
  let component: CreditTypeListComponent;
  let fixture: ComponentFixture<CreditTypeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreditTypeListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreditTypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
