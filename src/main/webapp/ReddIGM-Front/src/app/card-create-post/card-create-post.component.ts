import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-create-post',
  templateUrl: './card-create-post.component.html',
  styleUrls: ['./card-create-post.component.scss']
})
export class CardCreatePostComponent implements OnInit {
  isModalActive: boolean = false;
  constructor() { }

  ngOnInit(): void {
  }

  openModalCreatePost() {
    this.isModalActive = !this.isModalActive;
  }
}
