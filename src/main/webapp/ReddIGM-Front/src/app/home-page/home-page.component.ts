import { Component, OnInit } from '@angular/core';
import { PostService } from "../services/post.service";
import {PostModel} from "../models/post.model";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  posts: Array<PostModel> = [];

  constructor(private postService: PostService) {
    this.postService.getAllPosts().subscribe(data => {this.posts = data;});
  }

  ngOnInit(): void {
  }

}
