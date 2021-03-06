import { Component, OnInit, Input } from '@angular/core';
import { DataService } from '../services/data.service';
import { AuthenticationService } from "../services/authentication.service"
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})


export class NavBarComponent {
	searchText: string = "";
  isAuthentified: boolean = false;
  name: string | undefined;
  user: User | null = new User();
  picture: string = "";

  constructor(private dataService: DataService, private authenticationService: AuthenticationService, private userService: UserService) {
    this.isAuthentified = this.authenticationService.isLoggedIn();
  }

  ngOnInit(): void {
    this.user = this.authenticationService.getCurrentUser();
    let p = this.user?.getPicture();
    if(p != null){
      this.picture = p;
      this.name = this.user?.username;
    }
  }

  search(){
	  this.dataService.changeMessage(this.searchText)
  }

  logout() {
    this.authenticationService.logout();
  }
}
