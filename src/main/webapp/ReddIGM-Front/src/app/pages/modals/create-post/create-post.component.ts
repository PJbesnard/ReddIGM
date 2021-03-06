import { PostService } from './../../../services/post.service';
import { CreatePostPayload } from './create-post.payload';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
	postName:string = ""
	subjectName:string = "TestSubject"
	description:string = ""
	@Input() openModal: any
	
	@Output()
  	openModelChange = new EventEmitter<boolean>();

  constructor(private postService : PostService) { }

  ngOnInit(): void {
  }

  isModalActive: boolean = false;
  isDropdownActive: boolean = false;
  
  openDropdown() {
	this.isDropdownActive = !this.isDropdownActive;
  }
  closeModal() {
	this.openModelChange.emit(!this.openModal);
  }

  createPost(){
	    let payload  = new CreatePostPayload();
		payload.postName = this.postName
		payload.description = this.description
		this.postService.createPost(payload).subscribe((data) => this.closeModal())
  }





}
