import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';
import { VoteComment } from '../models/vote-comment.model';
import { VotePost } from '../models/vote-post.model';
import { OrderType } from '../utils/order-type.enum';

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
  
  getUserVotesPost(userId: number, orderType: OrderType = OrderType.DESCENDING): Observable<VotePost[]> {
    return this.httpClient.get<VotePost[]>(BASE_ADDRESS + "users/" + userId + "/votes/posts/" + orderType);
  }

  getUserVotesComments(userId: number, orderType: OrderType = OrderType.DESCENDING): Observable<VoteComment[]> {
    return this.httpClient.get<VoteComment[]>(BASE_ADDRESS + "users/" + userId + "/votes/comments/" + orderType);
  }

  updateUser(userId: number, newUser: User): Observable<User> {
    return this.httpClient.post<User>(BASE_ADDRESS + "users/" + userId, newUser);
  }

  updateUserPassword(userId: number, newPassword: string): Observable<User> {
    return this.httpClient.post<User>(BASE_ADDRESS + "users/editPassword/" + userId, newPassword);
  }
}
