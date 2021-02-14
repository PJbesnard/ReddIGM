import { Component, Input, OnInit } from '@angular/core';
import { Comment } from "../models/comment.model";

@Component({
  selector: 'app-comment-preview',
  templateUrl: './comment-preview.component.html',
  styleUrls: ['./comment-preview.component.scss']
})
export class CommentPreviewComponent implements OnInit {

  @Input()
  comment!: Comment;

  constructor() { }

  ngOnInit(): void {
  }

}
