import { CreateCommentPayload } from './create-comment.payload';
import { CreateSubcommentPayload } from './create-subcomment.payload';
import { ActivatedRoute, Router } from '@angular/router';

import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { CommentService } from '../services/comment.service';
@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.scss']
})
export class CreateCommentComponent implements OnInit {
	description:string = ""

	@Input() openModal: any
  @Input() postId: number = 0;
  @Input() superCommentId: number = 0;
	@Input() url: String = "/home";
	@Output()
  	openModelChange = new EventEmitter<boolean>();

  constructor(private commmentService : CommentService, private router: Router) {

   }

  ngOnInit(): void {
  }


  isModalActive: boolean = false;
  isDropdownActive: boolean = false;
  
  openDropdown() {
	  this.isDropdownActive = !this.isDropdownActive;
  }
  closeModal() {
	  this.openModelChange.emit(!this.openModal);
    this.router.navigateByUrl('' + this.url);

  }

  createComment(){
    if (this.superCommentId === 0){
      let payload  = new CreateCommentPayload();
      payload.postId = this.postId;
      payload.text = this.description;
      this.commmentService.createComment(payload).subscribe((data) => {
        this.closeModal()
        this.reset()
        window.location.reload()
      } )
    }else{
      let payload  = new CreateSubcommentPayload();
      payload.postId = this.postId;
      payload.text = this.description;
      payload.superCommentId = this.superCommentId;
      this.commmentService.createComment(payload).subscribe((data) => {
        this.closeModal()
        this.reset()
        window.location.reload()

      } )
    }

  }

  reset(){
    if(this.superCommentId === 0){
      let payload  = new CreateCommentPayload();
      payload.text = ""
      this.description= ""
    }else{
      let payload  = new CreateSubcommentPayload();
      payload.text = ""
      this.description= ""
    }
  }

}
