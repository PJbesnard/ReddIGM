import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faPlusCircle, faMinusCircle } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';
import { VoteType } from '../models/vote-type.enum';
import { PostService } from "../services/post.service"

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {
  faPlusCircle = faPlusCircle;
  faMinusCircle = faMinusCircle;

  upvote: VoteType = VoteType.UPVOTE;
  downvote: VoteType = VoteType.DOWNVOTE;
  novote: VoteType = VoteType.NOVOTE;
  isAuthentified: boolean = false;

  @Input() id: number = 8;
  @Input() author: string = "Michel"; //
  @Input() title: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() content: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() date: string = "5 days ago"; //
  @Input() subName: string = "r/truc"; //
  @Input() rate: number = 14; //utiliser number
  @Input() image: string = "https://ih1.redbubble.net/image.698410235.0273/flat,128x128,075,t.u2.jpg"; //utiliser number
  @Input() vote: VoteType = VoteType.NOVOTE;


  constructor(private router: Router, private authenticationService: AuthenticationService, private postService: PostService) {
    this.isAuthentified = this.authenticationService.isLoggedIn();
  }

  ngOnInit(): void {
  }

  displayPost(){
    this.router.navigate(['posts-from-sub/display-post', { id: this.id, subName: this.subName }]);
  }

  clickVote(userVote: VoteType) {
    if(!this.isAuthentified) {
      this.router.navigateByUrl('/login');
    }
    this.postService.vote(this.id, userVote).subscribe(data => {this.vote = data.myVote; this.rate = data.voteCount;});
  }

}
