import { Component, Input, OnInit } from '@angular/core';
import { Comment } from "../models/comment.model";
import { CommentService } from '../services/comment.service';

@Component({
  selector: 'app-comment-preview',
  templateUrl: './comment-preview.component.html',
  styleUrls: ['./comment-preview.component.scss']
})
export class CommentPreviewComponent implements OnInit {

  @Input()
  commentId!: number;

  comment: Comment = new Comment();

  constructor(private commentService: CommentService) {
  }

  ngOnInit(): void {
    this.commentService.getCommentById(this.commentId).subscribe(
      (response) => {
        this.comment = new Comment().deserialize(response);
      }
    );
  }

}
