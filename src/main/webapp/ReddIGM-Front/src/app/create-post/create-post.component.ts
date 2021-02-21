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

  saveChanges() {

  }

}
