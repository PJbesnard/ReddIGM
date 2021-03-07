import { Component, OnInit } from '@angular/core';
import { PostService } from "../services/post.service";
import {PostModel} from "../models/post.model";
import { User } from '../models/user.model';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  posts: Array<PostModel> = [];
  hot: boolean = true;
  sort: String = "DESCENDING";

  constructor(private postService: PostService) {
    this.postService.getAllPosts(this.sort).subscribe(data => {this.posts = data;});
  }

  ngOnInit(): void {
  }

  newOnclick(){
    this.sort = "NEWEST";
    this.hot = false;
    this.postService.getAllPosts(this.sort).subscribe(data => {this.posts = data;});
  }

  hotOnclick(){
    this.sort = "DESCENDING";
    this.hot = true;
    this.postService.getAllPosts(this.sort).subscribe(data => {this.posts = data;});
  }

  getPicture(user: User): string {
    return user.getPicture()
  }


}
