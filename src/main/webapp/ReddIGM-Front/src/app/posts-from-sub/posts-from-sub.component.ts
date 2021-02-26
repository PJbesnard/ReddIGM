import { Component, OnInit } from '@angular/core';
import {PostService} from "../services/post.service"
import {PostModel} from "../models/post.model"
import {SubjectModel} from "../models/subject-response"
import {SubjectService} from "../services/subject.service"

@Component({
  selector: 'app-posts-from-sub',
  templateUrl: './posts-from-sub.component.html',
  styleUrls: ['./posts-from-sub.component.scss']
})
export class PostsFromSubComponent implements OnInit {
  posts: Array<PostModel> = [];
  subId: number = 0;
  sub?: string;
  constructor(private postService: PostService, private subjectService: SubjectService) { }

  ngOnInit(): void {
    this.subjectService.getSubject(this.subId).subscribe(data => this.sub = data.name)
    this.postService.getPostsBySubject(this.subId).subscribe(data => this.posts = data);
  }

}
