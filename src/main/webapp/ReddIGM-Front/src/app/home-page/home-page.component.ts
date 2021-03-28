import { Component, HostListener, Inject, OnInit } from '@angular/core';
import { PostService } from "../services/post.service";
import {PostModel} from "../models/post.model";
import { Subscription } from 'rxjs';
import { DataService } from '../services/data.service';
import { AuthenticationService } from '../services/authentication.service';
import { faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { WindowService } from '../services/window.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  @HostListener('window:scroll', ['$event']) onWindowScroll(e: any) {
    let scrollTop = (document.documentElement.scrollTop || document.body.scrollTop);
    let innerHeight = this.windowService.windowRef.innerHeight;
    let scrollHeight = document.documentElement.scrollHeight;

    if ((innerHeight + scrollTop) >= scrollHeight) {
      this.onBottomPage();
    }
  }

  posts: Array<PostModel> = [];
  hot: boolean = true;
  sort: String = "DESCENDING";
  currentPosts: Array<PostModel> = [];
  subscription!: Subscription;
  message:string = "";
  faTimesCircle = faTimesCircle;
  isScrolled: boolean = false;

  constructor(private windowService: WindowService, private postService: PostService, private dataService: DataService, private authenticationService: AuthenticationService) {
    this.postService.getAllPosts(this.sort).subscribe(data => {this.posts = data;});
  }

  ngOnInit(): void {
    this.postService.getAllPosts(this.sort).subscribe(data => {
      this.currentPosts = data;
      this.posts = data;
      this.currentPosts = this.currentPosts.map(post => new PostModel().deserialize(post));
      this.posts = this.posts.map(post => new PostModel().deserialize(post));
    });
    this.subscription = this.dataService.currentMessage.subscribe(message => this.search(message));
  }

  newOnclick(){
    this.sort = "NEWEST";
    this.hot = false;
    this.ngOnInit();
  }

  hotOnclick(){
    this.sort = "DESCENDING";
    this.hot = true;
    this.ngOnInit();
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

  onBottomPage() {}
}
