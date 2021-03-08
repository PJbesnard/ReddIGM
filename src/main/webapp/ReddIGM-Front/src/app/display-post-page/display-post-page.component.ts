import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import { PostViewInContextComponent } from '../post-view-in-context/post-view-in-context.component';
import { PostService } from '../services/post.service';
import { PostModel } from '../models/post.model';


@Component({
  selector: 'app-display-post-page',
  templateUrl: './display-post-page.component.html',
  styleUrls: ['./display-post-page.component.scss']
})
export class DisplayPostPageComponent implements OnInit {
  post: PostModel = new PostModel();
  subjectName: string = "";
  subjectId: String = "0";


  constructor(private route: ActivatedRoute,
    private router: Router, private postService: PostService  ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');

    if (id != undefined) {
      this.postService.getPost(parseInt(id)).subscribe(
        (response) => {
          this.post = response;
        }
      );
      this.subjectName = this.route.snapshot.paramMap.get('subName')!.toString();
      this.subjectId = this.route.snapshot.paramMap.get('subId')!.toString();
    }
  }

  displaySub(){
    this.router.navigateByUrl('/posts-from-sub/'+this.subjectId);
  }



}
