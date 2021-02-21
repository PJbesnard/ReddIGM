import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card-create-sub',
  templateUrl: './card-create-sub.component.html',
  styleUrls: ['./card-create-sub.component.scss']
})
export class CardCreateSubComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  onClick(): void {
    this.router.navigateByUrl('');
  }
}
