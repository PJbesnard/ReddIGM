import { Component, Input, OnInit } from '@angular/core';
import { faChevronCircleUp, faPlusCircle, faReply, faMinusCircle, faCommentAlt } from '@fortawesome/free-solid-svg-icons';

import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Comment } from '../models/comment.model';
import { CommentService } from '../services/comment.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-post-view-in-context',
  templateUrl: './post-view-in-context.component.html',
  styleUrls: ['./post-view-in-context.component.scss']
})

@Injectable()
export class PostViewInContextComponent implements OnInit {
 
  showComments = false;

  appareilsSubject = new Subject<any[]>();

  comments: Array<Comment> = [];

  isModalActive: boolean = false;

  faPlusCircle = faPlusCircle;
  faMinusCircle = faMinusCircle;
  faCommentAlt = faCommentAlt;
  faReply = faReply;
  faChevronCircleUp = faChevronCircleUp;
  sort: String = "DESCENDING";
  hot: boolean = true;


  @Input() type: string = "post";
  @Input() id: number = 1;
  @Input() author: string = "Michel"; //
  @Input() title: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() content: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() date: string = "5 days ago"; //
  @Input() subName: string = "r/truc"; //
  @Input() rate: number = 14; //utiliser number
  @Input() image: string = "https://ih1.redbubble.net/image.698410235.0273/flat,128x128,075,t.u2.jpg"; //utiliser number
  @Input() responseNb: number = 182; 

  
  constructor(private commentService: CommentService, private router: Router) {
  }

  ngOnInit(): void {
    console.log ("id ", this.id ); // function called s
  }

  displayResponses(){
    this.showComments = true
    if (this.type === "post"){
      this.commentService.getCommentsFromPost(this.sort, this.id).subscribe(data =>{
        console.log("data " + data)

        this.comments = data;
      });
    }
    
    if (this.type === "comment"){
      this.commentService.getCommentsForComment(this.sort, this.id).subscribe(data =>{
        this.comments = data;
      });
    }
  }
  
  openModalCreatePost() {
    this.isModalActive = !this.isModalActive;
  }

  hideResponses() {
    this.showComments = false;
    this.comments = [];
  }

  displayAuthor(){
    this.router.navigate(['users/1', { id: this.id, subName: this.subName }]);
  }

  newOnclick(){
    this.sort = "NEWEST";
    this.hot = false;
    this.displayResponses();
  }

  hotOnclick(){
    this.sort = "DESCENDING";
    this.hot = true;
    this.displayResponses();
  }
  
  

}
