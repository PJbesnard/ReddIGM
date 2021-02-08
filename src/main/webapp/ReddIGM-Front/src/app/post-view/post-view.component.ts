import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {
  
  @Input() author: string = "Michel"; //
  @Input() title: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() content: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() date: string = "5 days ago"; //
  @Input() subName: string = "r/truc"; //
  @Input() rate: string = "1"; //utiliser number


  constructor() { }

  ngOnInit(): void {
  }

}
