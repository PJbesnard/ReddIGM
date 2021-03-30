import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private commentService: CommentService, private router: Router) {
  }

  ngOnInit(): void {
    this.commentService.getCommentById(this.commentId).subscribe(
      (response) => {
        this.comment = new Comment().deserialize(response);
      }
    );
  }

  goToPost() {
    this.router.navigate(["/display-post/", {id: this.comment.postId}]);
  }

  goToUser() {
    this.router.navigateByUrl("/users/" + this.comment.user.id);
  }
}
