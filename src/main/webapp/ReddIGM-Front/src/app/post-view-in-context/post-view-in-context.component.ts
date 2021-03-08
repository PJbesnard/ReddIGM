import { Component, Input, OnInit } from '@angular/core';
import { faChevronCircleUp, faPlusCircle, faReply, faMinusCircle, faCommentAlt, faTimesCircle } from '@fortawesome/free-solid-svg-icons';

import { Subject, Subscription } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Comment } from '../models/comment.model';
import { CommentService } from '../services/comment.service';
import { ActivatedRoute, Router } from '@angular/router';
import { VoteType } from '../models/vote-type.enum';
import { AuthenticationService } from '../services/authentication.service';
import { PostService } from "../services/post.service"
import { CreateCommentComponent} from "../create-comment/create-comment.component";
import { DataService } from '../services/data.service';





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
  currentComments: Array<Comment> = [];
  subscription!: Subscription;

  isModalActive: boolean = false;

  faPlusCircle = faPlusCircle;
  faMinusCircle = faMinusCircle;
  faCommentAlt = faCommentAlt;
  faReply = faReply;
  faChevronCircleUp = faChevronCircleUp;
  faTimesCircle = faTimesCircle;
  sort: String = "DESCENDING";
  hot: boolean = true;

  upvote: VoteType = VoteType.UPVOTE;
  downvote: VoteType = VoteType.DOWNVOTE;
  novote: VoteType = VoteType.NOVOTE;
  isAuthentified: boolean = false;

  crossComment: boolean = false;


  @Input() commentNumber: number = 0;
  @Input() authorId: number = 1;
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
  @Input() postId: number = 0;
  @Input() vote: VoteType = VoteType.NOVOTE;
  @Input() isAdmin: boolean = false;


  constructor(private commentService: CommentService,
	private postService: PostService,
	private authenticationService: AuthenticationService,
	private router: Router,
	private dataService: DataService) {
    this.isAuthentified = this.authenticationService.isLoggedIn();
  }

  ngOnInit(): void {
    if(this.authenticationService.getCurrentUser()?.username === this.author || this.isAdmin) this.crossComment = true;


	
	this.subscription = this.dataService.currentMessage.subscribe(message => this.search(message));

  }

  displayResponses(){
    this.showComments = true
    if (this.type === "post"){
      this.commentService.getCommentsFromPost(this.sort, this.id).subscribe(data =>{
        console.log("data " + data)
        this.comments = data;
		this.currentComments = data
      });
    }

    if (this.type === "comment"){
      this.commentService.getCommentsForComment(this.sort, this.id).subscribe(data =>{
        this.comments = data;
		this.currentComments = data
      });
    }
  }

  deleteId(id: number) {
    if(this.type === "post") this.postService.deletePost(id);
    this.commentService.deleteComment(id);
  }

  openModalCreatePost() {
    this.isModalActive = !this.isModalActive;
  }

  hideResponses() {
    this.showComments = false;
    this.comments = [];
	this.currentComments = [];
  }

  displayAuthor(){
    this.router.navigateByUrl('users/' + this.authorId);
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

  clickVote(userVote: VoteType) {
    if(!this.isAuthentified) {
      this.router.navigateByUrl('/login');
    }
    if (this.type === "post"){
      this.postService.vote(this.id, userVote).subscribe(data => {this.vote = data.myVote; this.rate = data.voteCount;});
    }else{
      console.log("clic vote comment")
      this.commentService.vote(this.id, userVote).subscribe(data => {this.vote = data.myVote; this.rate = data.nbVote;});
    }
  }

    search(searchText : string){
	if (!this.comments) {
		this.currentComments = [];
		console.log(this.currentComments)
	}
	  if (!searchText) {
		this.currentComments = this.comments;
		console.log(this.currentComments)
	  }
	  searchText = searchText.toLocaleLowerCase();

	  this.currentComments = this.comments.filter(it => {
		return it.text.toLocaleLowerCase().includes(searchText);

	  })
  }


}
