import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';
import { UserService } from './user.service';

const BASE_ADDRESS = environment.baseURL;

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient, private router: Router, private userService: UserService) { }

  register(username: string, password: string, email: string) {
    const body = {
      username: username,
      password: password,
      email: email
    };

    this.httpClient.post<any>(BASE_ADDRESS + "register", body).subscribe(
      (response) => {
        this.router.navigate(["/home"]);
      },
      (response) => {
        console.error(response);
        // TODO : Rediriger vers une page d'erreur
      }
    );
  }

  login(username: string, password: string) {
    const body = {
      username: username,
      password: password
    };

    this.httpClient.post<any>(BASE_ADDRESS + "login", body).subscribe(
      (response) => {
        localStorage.setItem("jwt", response["jwt"]);

        this.userService.getUserByUsernameWithJwt(username, response["jwt"]).subscribe(
          (response) => {
            localStorage.setItem("currentUser", JSON.stringify(response));
          }
        );

        this.router.navigate(["/home"]);
      },
      (response) => {
        console.error(response);
        // TODO : Rediriger vers la page de connexion avec une erreur d'authentification
      }
    );
  }
}
