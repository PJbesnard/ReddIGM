import { PostService } from './../../../services/post.service';
import { CreatePostPayload } from './create-post.payload';
import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private postService : PostService,
	private route: ActivatedRoute,
	private router: Router) { }

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
		let pathname = window.location.pathname
		let id = pathname.substring(pathname.lastIndexOf('/') + 1)
		payload.subjectId = id
		payload.postName = this.postName
		payload.description = this.description
		this.postService.createPost(payload).subscribe((data) => {
			this.closeModal()
			this.reset()
		} )
  }

  reset(){
	let payload  = new CreatePostPayload();
	payload.postName = ""
	payload.description = ""
	this.postName= ""
	this.description= ""
	this.reloadComponent()

  }

	reloadComponent() {
		let currentUrl = this.router.url;
			this.router.routeReuseStrategy.shouldReuseRoute = () => false;
			this.router.onSameUrlNavigation = 'reload';
			this.router.navigate([currentUrl]);
		}
}
