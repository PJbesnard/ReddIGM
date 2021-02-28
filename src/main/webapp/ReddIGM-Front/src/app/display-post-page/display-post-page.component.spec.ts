import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayPostPageComponent } from './display-post-page.component';

describe('DisplayPostPageComponent', () => {
  let component: DisplayPostPageComponent;
  let fixture: ComponentFixture<DisplayPostPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayPostPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayPostPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
