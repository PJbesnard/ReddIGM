import { Component, ViewChild, ElementRef  } from '@angular/core';
import { DataService } from '../services/data.service';
import { AuthenticationService } from "../services/authentication.service"
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})


export class NavBarComponent {
  @ViewChild('navBurger') navBurger!: ElementRef;
  @ViewChild('navMenu') navMenu!: ElementRef;

	searchText: string = "";
  isAuthentified: boolean = false;
  name: string | undefined;
  user: User | null = new User();
  picture: string | undefined = "";

  constructor(private dataService: DataService, private authenticationService: AuthenticationService, private userService: UserService, private router: Router) {
    this.isAuthentified = this.authenticationService.isLoggedIn();
  }

  ngOnInit(): void {
    if(this.isAuthentified) {
      this.user = this.authenticationService.getCurrentUser();
      this.picture = this.user?.getPicture();
      this.name = this.user?.username;
    }
  }

  toggleNavbar() {
    this.navBurger.nativeElement.classList.toggle('is-active');
    this.navMenu.nativeElement.classList.toggle('is-active');
}

  search(){
	  this.dataService.changeMessage(this.searchText)
  }

  logout() {
    this.authenticationService.logout();
    window.location.reload();
  }

  goToProfile() {
    this.router.navigate(["/users/" + this.user?.id]);
  }
}
