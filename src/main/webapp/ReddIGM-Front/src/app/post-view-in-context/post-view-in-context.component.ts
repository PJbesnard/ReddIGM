import { Component, Input, OnInit } from '@angular/core';
import { faPlusCircle, faMinusCircle, faCommentAlt, faChevronCircleDown } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-post-view-in-context',
  templateUrl: './post-view-in-context.component.html',
  styleUrls: ['./post-view-in-context.component.scss']
})
export class PostViewInContextComponent implements OnInit {
  isModalActive: boolean = false;
  faPlusCircle = faPlusCircle;
  faMinusCircle = faMinusCircle;
  faCommentAlt = faCommentAlt;
  faChevronCircleDown = faChevronCircleDown;
  @Input() author: string = "Michel"; //
  @Input() title: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() content: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() date: string = "5 days ago"; //
  @Input() subName: string = "r/truc"; //
  @Input() rate: number = 14; //utiliser number
  @Input() image: string = "https://ih1.redbubble.net/image.698410235.0273/flat,128x128,075,t.u2.jpg"; //utiliser number


  constructor() { }

  ngOnInit(): void {
  }
  

  openModalCreatePost() {
    this.isModalActive = !this.isModalActive;
  }


}
