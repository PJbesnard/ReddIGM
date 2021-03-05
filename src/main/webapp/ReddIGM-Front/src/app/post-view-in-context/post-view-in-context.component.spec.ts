import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostViewInContextComponent } from './post-view-in-context.component';

describe('PostViewInContextComponent', () => {
  let component: PostViewInContextComponent;
  let fixture: ComponentFixture<PostViewInContextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostViewInContextComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostViewInContextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
