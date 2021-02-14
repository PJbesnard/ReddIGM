import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

const BASE_ADDRESS = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getUser(username: string): Observable<User> {
    const body = {
      username: username
    };

    // TODO
    // A commenter lorsque la fonction du back sera dispo
    return new Observable<User>();

    /* A décommenter lorsque la fonction du back sera dispo
    return this.httpClient.post<User>(BASE_ADDRESS + "???", body);
    */
  }
}
