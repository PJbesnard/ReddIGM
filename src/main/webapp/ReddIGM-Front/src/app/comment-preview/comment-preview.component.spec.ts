import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentPreviewComponent } from './comment-preview.component';

describe('CommentPreviewComponent', () => {
  let component: CommentPreviewComponent;
  let fixture: ComponentFixture<CommentPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentPreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
