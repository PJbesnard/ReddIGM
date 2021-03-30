import { Component, OnInit } from '@angular/core';
import { PostService } from '../services/post.service';
import { PostModel} from '../models/post.model'
@Component({
  selector: 'app-card-create-post',
  templateUrl: './card-create-post.component.html',
  styleUrls: ['./card-create-post.component.scss']
})
export class CardCreatePostComponent implements OnInit {
  isModalActive: boolean = false;
  test: any;
  post!: PostModel;
  constructor(
	  private postService: PostService
  ) { }

  ngOnInit(): void {
	  //test
	//   this.postService.getPost("1").subscribe( reponse => {this.post = reponse})

  }

  openModalCreatePost() {
    this.isModalActive = !this.isModalActive;
  }
}
