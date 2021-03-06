import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardCreatePostComponent } from './card-create-post.component';

describe('CardCreatePostComponent', () => {
  let component: CardCreatePostComponent;
  let fixture: ComponentFixture<CardCreatePostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardCreatePostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardCreatePostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
