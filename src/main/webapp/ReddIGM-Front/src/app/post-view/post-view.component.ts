import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faPlusCircle, faMinusCircle } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {
  faPlusCircle = faPlusCircle;
  faMinusCircle = faMinusCircle;

  @Input() id: number = 6;
  @Input() author: string = "Michel"; //
  @Input() title: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() content: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() date: string = "5 days ago"; //
  @Input() subName: string = "r/truc"; //
  @Input() rate: number = 14; //utiliser number
  @Input() image: string = "https://ih1.redbubble.net/image.698410235.0273/flat,128x128,075,t.u2.jpg"; //utiliser number


  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  
  displayPost(){
    this.router.navigate(['posts-from-sub/display-post', { id: this.id }]);
  }

}
