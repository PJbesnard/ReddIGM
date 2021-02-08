import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-browse-subs',
  templateUrl: './browse-subs.component.html',
  styleUrls: ['./browse-subs.component.scss']
})
export class BrowseSubsComponent implements OnInit {
  subs: string[] = ['r/IGMFacts', 'r/IGMCodingLife', 'r/IGMCrazyCoders', 'r/IGMCoderLifeStyle', 'r/IGMLoveUForax'];
  constructor() { }

  ngOnInit(): void {
  }

}
