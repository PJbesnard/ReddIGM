import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';

const BASE_ADDRESS = environment.baseURL;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getUserById(userId: number): Observable<User> {
    return this.httpClient.get<User>(BASE_ADDRESS + "users/" + userId);
  }

  getUserByUsername(username: string): Observable<User> {
    return this.httpClient.get<User>(BASE_ADDRESS + "users/usernames/" + username);
  }

  getUserByEmail(email: string): Observable<User> {
    return this.httpClient.get<User>(BASE_ADDRESS + "users/emails/" + email);
  }
  
}
