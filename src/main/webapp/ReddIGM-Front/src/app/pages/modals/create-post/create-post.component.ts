import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
	@Input() openModal: any
	
	@Output()
  	openModelChange = new EventEmitter<boolean>();

  constructor() { }

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

//   createPost() {
//     this.postPayload.postName = this.createPostForm.get('postName').value;
//     this.postPayload.subredditName = this.createPostForm.get('subredditName').value;
//     this.postPayload.url = this.createPostForm.get('url').value;
//     this.postPayload.description = this.createPostForm.get('description').value;

//     this.postService.createPost(this.postPayload).subscribe((data) => {
//       this.router.navigateByUrl('/');
//     }, error => {
//       throwError(error);
//     })
//   }



}
