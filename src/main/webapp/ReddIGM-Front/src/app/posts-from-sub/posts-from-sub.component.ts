import { Component, OnInit } from '@angular/core';
import {PostService} from "../services/post.service"
import {PostModel} from "../models/post.model"
import { ActivatedRoute } from '@angular/router';
import {SubjectService} from "../services/subject.service"
import {SubjectModel} from "../models/subject-response"
import {UserService} from "../services/user.service"
import {User} from "../models/user.model"
import { Subscription } from 'rxjs';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-posts-from-sub',
  templateUrl: './posts-from-sub.component.html',
  styleUrls: ['./posts-from-sub.component.scss']
})
export class PostsFromSubComponent implements OnInit {
  posts: Array<PostModel> = [];
  subId: number;
  subName: string = "";
  subDescription: string = "";
  hot: boolean = true;
  sort: String = "DESCENDING";
  currentPosts: Array<PostModel> = [];
  subscription!: Subscription;
  message:string = "";

  constructor(private postService: PostService,
	private subjectService: SubjectService,
	private userService: UserService,
	private route: ActivatedRoute,
	private dataService: DataService) {
    this.subId = this.route.snapshot.params['id'];
    this.postService.getPostsBySubject(this.subId, this.sort).subscribe(data => {this.posts = data;});
    this.subjectService.getSubject(this.subId).subscribe(data => {this.subName = data.name; this.subDescription = data.description;});
  }

  ngOnInit(): void {
	this.postService.getPostsBySubject(this.subId, this.sort).subscribe(data => {
		this.currentPosts = data;
		this.posts = data;
	});
	this.subscription = this.dataService.currentMessage.subscribe(message => this.search(message));
  }

  getUser(userId: number): User {
    let user: User = new User();
    this.userService.getUserById(userId).subscribe(data => user = data);
    return user;
  }

  newOnclick(){
    this.sort = "NEWEST";
    this.hot = false;
    this.postService.getPostsBySubject(this.subId, this.sort).subscribe(data => {this.posts = data;this.currentPosts = data});
  }

  hotOnclick(){
    this.sort = "DESCENDING";
    this.hot = true;
    this.postService.getPostsBySubject(this.subId, this.sort).subscribe(data => {this.posts = data;this.currentPosts = data});
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
