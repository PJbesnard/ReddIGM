import { PostModel } from './../models/post.model';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DataService } from '../services/data.service';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})


export class NavBarComponent {
	searchText: string = "";


  constructor( private dataService: DataService ) {}


  search(){
	  this.dataService.changeMessage(this.searchText)

  }

}
