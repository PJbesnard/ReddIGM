import { Component, OnInit, Input } from '@angular/core';
import { DataService } from '../services/data.service';
import { AuthenticationService } from "../services/authentication.service"


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})


export class NavBarComponent {
	searchText: string = "";
  isAuthentified: boolean = false;

  constructor(private dataService: DataService, private authenticationService: AuthenticationService) {
    this.isAuthentified = this.authenticationService.isLoggedIn();
  }


  search(){
	  this.dataService.changeMessage(this.searchText)
  }

  logout() {
    this.authenticationService.logout();
  }
}
