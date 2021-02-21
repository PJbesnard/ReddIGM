import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSubComponent } from './create-sub.component';

describe('CreateSubComponent', () => {
  let component: CreateSubComponent;
  let fixture: ComponentFixture<CreateSubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateSubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
