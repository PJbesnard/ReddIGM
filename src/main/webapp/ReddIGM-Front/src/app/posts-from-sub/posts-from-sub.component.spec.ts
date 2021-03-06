import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostsFromSubComponent } from './posts-from-sub.component';

describe('PostsFromSubComponent', () => {
  let component: PostsFromSubComponent;
  let fixture: ComponentFixture<PostsFromSubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostsFromSubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostsFromSubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
