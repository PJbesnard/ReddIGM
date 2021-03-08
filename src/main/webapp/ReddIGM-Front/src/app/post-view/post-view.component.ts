import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faPlusCircle, faMinusCircle, faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';
import { VoteType } from '../models/vote-type.enum';
import { PostService } from "../services/post.service"
import { PostModel } from '../models/post.model';

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {
  faPlusCircle = faPlusCircle;
  faMinusCircle = faMinusCircle;
  faTimesCircle = faTimesCircle;

  upvote: VoteType = VoteType.UPVOTE;
  downvote: VoteType = VoteType.DOWNVOTE;
  novote: VoteType = VoteType.NOVOTE;
  isAuthentified: boolean = false;
  isAdmin: boolean | undefined = false;

  @Input() id: number = 0;
  @Input() author: string = "";
  @Input() title: string = "";
  @Input() content: string = "";
  @Input() date: string = "";
  @Input() subName: string = "";
  @Input() rate: number = 0;
  @Input() image: string = "";
  @Input() vote: VoteType = VoteType.NOVOTE;
  @Input() subId: number | undefined = 0;
  @Input() authorId: number = 0;
  @Input() post: PostModel | undefined;
  @Input() loaded: boolean = true; // If false, forces the component to load infos from backend with the id


  constructor(private router: Router, private authenticationService: AuthenticationService, private postService: PostService) {
  }

  ngOnInit(): void {
    this.isAuthentified = this.authenticationService.isLoggedIn();
    this.isAdmin = this.authenticationService.getCurrentUser()?.isAdmin()
    if (!this.loaded) {
      if (this.post) {
        this.id = this.post.id;
        this.author = this.post.user.username;
        this.title = this.post.postName;
        this.content = this.post.description;
        this.date = this.post.duration;
        this.subName = this.post.sub.name;
        this.rate = this.post.voteCount;
        this.vote = this.post.myVote;
        this.subId = this.post.sub.id;
        this.authorId = this.post.user.id;
      } else {
        this.postService.getPost(this.id).subscribe((response) => {
          const post = new PostModel().deserialize(response);

          this.id = post.id;
          this.author = post.user.username;
          this.title = post.postName;
          this.content = post.description;
          this.date = post.duration;
          this.subName = post.sub.name;
          this.rate = post.voteCount;
          this.vote = post.myVote;
          this.subId = post.sub.id;
          this.authorId = post.user.id;
        });
      }
    }
  }

  displayPost(){
    this.router.navigate(['display-post', { id: this.id, subName: this.subName, subId: this.subId }]);
  }

  clickVote(userVote: VoteType) {
    if(!this.isAuthentified) {
      this.router.navigateByUrl('/login');
    }
    this.postService.vote(this.id, userVote).subscribe(data => {this.vote = data.myVote; this.rate = data.voteCount;});
  }

  clickSub() {
    this.router.navigateByUrl('posts-from-sub/'+this.subId);
  }

  clickProfile() {
    this.router.navigateByUrl('users/'+this.authorId);
  }

  deleteId(id: number) {
    this.postService.deletePost(id).subscribe(data => window.location.reload());
  }
}
