import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostModel } from '../models/post.model';
import { User } from '../models/user.model';
import { Comment } from '../models/comment.model';
import { AuthenticationService } from '../services/authentication.service';
import { CommentService } from '../services/comment.service';
import { PostService } from '../services/post.service';
import { UserService } from '../services/user.service';
import { VotePost } from '../models/vote-post.model';
import { VoteComment } from '../models/vote-comment.model';
import { VoteType } from '../models/vote-type.enum';
import { Datable } from '../models/datable.model';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  user: User = new User();
  chronologicalHistory: Datable[] = [];
  chronologicalHistoryFiltered: Datable[] = [];
  posts: PostModel[] = [];
  comments: Comment[] = [];
  votesPosts: VotePost[] = [];
  votesComments: VoteComment[] = [];
  nbLikes: number = 0;
  nbDislikes: number = 0;
  isMe: boolean = false;

  constructor(private userService: UserService, private route: ActivatedRoute, private authService: AuthenticationService, private router: Router,
              private postService: PostService, private commentService: CommentService) { }

  async initUserValues(user: User) {
    this.user = user;

    // Retrieving posts
    this.posts = await this.postService.getPostsByUserId(user.id).toPromise();

    // Retrieving comments
    this.comments = await this.commentService.getCommentsByUsername(user.username).toPromise();

    // Retrieving votes posts
    this.votesPosts = await this.userService.getUserVotesPost(user.id).toPromise();

    // Retrieving votesComment
    this.votesComments = await this.userService.getUserVotesComments(user.id).toPromise();

    // Likes and dislikes
    this.nbLikes = this.votesPosts.filter(vote => vote.type === VoteType.UPVOTE).length +
                  this.votesComments.filter(vote => vote.type === VoteType.UPVOTE).length;

    this.nbDislikes = this.votesPosts.filter(vote => vote.type === VoteType.DOWNVOTE).length +
                      this.votesComments.filter(vote => vote.type === VoteType.DOWNVOTE).length;

    // Adding all elements to an unified array
    this.posts.forEach(post => this.chronologicalHistory.push(post));
    this.comments.forEach(comment => this.chronologicalHistory.push(comment));
    this.votesPosts.forEach(votePost => this.chronologicalHistory.push(votePost));
    this.votesComments.forEach(voteComment => this.chronologicalHistory.push(voteComment));

    // Sorting this array chronologically 
    this.chronologicalHistory.sort((o1, o2) => {
      return o2.getDate().getTime() - o1.getDate().getTime();
    });

    // Initializating filtered array
    this.onFilterChange("All");
  }

  onFilterChange(selectedFilter: string) {
    this.chronologicalHistoryFiltered = this.chronologicalHistory.filter(element => {
      switch (selectedFilter) {
        case "All": {
          return true;
        }
        case "Posts": {
          return element instanceof PostModel;
        }
        case "Comments": {
          return element instanceof Comment;
        }
        case "Votes": {
          return element instanceof VotePost || element instanceof VoteComment;
        }
        default: {
          return false;
        } 
      }
    });
  }

  ngOnInit(): void {
    let userId = this.route.snapshot.params['id'];
    this.userService.getUserById(userId).subscribe(
      (response) => {
        this.user = new User().deserialize(response);
        this.initUserValues(this.user);
        this.isMe = userId == this.authService.getCurrentUser()?.id;
      },
      (response) => {
        console.error(response);
      }
    );
  }

  onEdit() {
    this.router.navigate(["/users/" + this.user.id + "/edit"], {skipLocationChange: true});
  }

}
