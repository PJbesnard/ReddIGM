import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardCreateSubComponent } from './card-create-sub.component';

describe('CardCreateSubComponent', () => {
  let component: CardCreateSubComponent;
  let fixture: ComponentFixture<CardCreateSubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardCreateSubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardCreateSubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
