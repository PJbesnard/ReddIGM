import { Component, OnInit } from '@angular/core';
import { PostService } from "../services/post.service";
import {PostModel} from "../models/post.model";
import { User } from '../models/user.model';
import { Subscription } from 'rxjs';
import { DataService } from '../services/data.service';
import { AuthenticationService } from '../services/authentication.service';
import { faTimesCircle } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  posts: Array<PostModel> = [];
  hot: boolean = true;
  sort: String = "DESCENDING";
  currentPosts: Array<PostModel> = [];
  subscription!: Subscription;
  message:string = "";
  faTimesCircle = faTimesCircle;

  constructor(private postService: PostService, private dataService: DataService, private authenticationService: AuthenticationService) {
    this.postService.getAllPosts(this.sort).subscribe(data => {this.posts = data;});
  }

  ngOnInit(): void {
	this.postService.getAllPosts(this.sort).subscribe(data => {
		this.currentPosts = data;
		this.posts = data;
	});
	this.subscription = this.dataService.currentMessage.subscribe(message => this.search(message));
  }

  newOnclick(){
    this.sort = "NEWEST";
    this.hot = false;
    this.postService.getAllPosts(this.sort).subscribe(data => {this.posts = data;this.currentPosts = data});
  }

  hotOnclick(){
    this.sort = "DESCENDING";
    this.hot = true;
    this.postService.getAllPosts(this.sort).subscribe(data => {this.posts = data;this.currentPosts = data});
  }

  getPicture(user: User): string {
    return user.getPicture()
  }
  search(searchText : string){
	if (!this.posts) {
		this.currentPosts = [];
		console.log(this.currentPosts)
	}
	  if (!searchText) {
		this.currentPosts = this.posts;
		console.log(this.currentPosts)
	  }
	  searchText = searchText.toLocaleLowerCase();

	  this.currentPosts = this.posts.filter(it => {
		return it.postName.toLocaleLowerCase().includes(searchText);

	  })
  }


}
