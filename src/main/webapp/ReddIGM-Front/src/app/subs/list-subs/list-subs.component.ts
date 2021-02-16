import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-list-subs',
  templateUrl: './list-subs.component.html',
  styleUrls: ['./list-subs.component.scss']
})
export class ListSubsComponent implements OnInit {
  @Input() subName: string = "r/IGMpasterribleleblaze";

  constructor() { }

  ngOnInit(): void {
  }

}
