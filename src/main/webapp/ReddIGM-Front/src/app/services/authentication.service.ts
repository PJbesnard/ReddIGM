import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';
import { JwtService } from './jwt.service';
import { UserService } from './user.service';

const BASE_ADDRESS = environment.baseURL;

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient, private userService: UserService, private jwtService: JwtService) { }

  register(username: string, password: string, email: string, successCallback?: () => void, failureCallback?: (errorMsg: string) => void) {
    const body = {
      username: username,
      password: password,
      email: email
    };

    this.userService.getUserByUsername(username).subscribe(
      (response) => {
        if (failureCallback != undefined) {
          failureCallback("Register failed : Username already taken");
        }
      },
      (response) => {

        this.userService.getUserByEmail(email).subscribe(
          (response) => {
            if (failureCallback != undefined) {
              failureCallback("Register failed : Email address already taken");
            }
          },
          (response) => {

            this.httpClient.post<any>(BASE_ADDRESS + "register", body).subscribe(
              (response) => {
                if (successCallback != undefined) {
                  successCallback();
                }
              },
              (response) => {
                if (failureCallback != undefined) {
                  failureCallback("Register failed : " + response.message);
                }
              }
            )

          }
        )

      }
    )
  }

  login(username: string, password: string, successCallback?: () => void, failureCallback?: (errorMsg: string) => void) {
    const body = {
      username: username,
      password: password
    };

    this.httpClient.post<any>(BASE_ADDRESS + "login", body).subscribe(
      (response) => {
        this.jwtService.setToken(response["jwt"]);

        this.userService.getUserByUsername(username).subscribe(
          (response) => {
            this.jwtService.setUser(response);
            if (successCallback != undefined) {
              successCallback();
            }
          }
        );
      },
      (response) => {
        if (failureCallback != undefined) {
          failureCallback("Authentication failed : Invalid username or password");
        }
      }
    );
  }

  logout(): void {
    this.jwtService.removeToken();
    this.jwtService.removeUser();
  }

  isLoggedIn(): boolean {
    return this.jwtService.getToken() != null;
  }

  getCurrentUser(): User | null {
    return this.jwtService.getUser();
  }
}
