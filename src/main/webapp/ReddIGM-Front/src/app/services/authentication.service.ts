import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_ADDRESS = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  jwtToken: string;

  constructor(private httpClient: HttpClient) {
    this.jwtToken = "";
  }

  register(username: String, password: String, email: String) {
    const body = {
      username: username,
      password: password,
      email: email
    };

    this.httpClient.post<any>(BASE_ADDRESS + "register", body).subscribe(
      (response) => {
        // TODO : Redirection vers la home page
      },
      (response) => {
        // TODO : Rediriger vers une page d'erreur
      }
    );
  }

  login(username: String, password: String) {
    const body = {
      username: username,
      password: password
    };

    this.httpClient.post<any>(BASE_ADDRESS + "login", body).subscribe(
      (response) => {
        this.jwtToken = response["jwt"];
        // TODO : Redirection vers la home page
      },
      (response) => {
        console.log(response);
        // TODO : Rediriger vers la page de connexion avec une erreur d'authentification
      }
    );
  }

  isUserExists(username: string): Observable<Boolean> {
    let params = new HttpParams();

    params = params.append('username', username);

    // TODO
    // A commenter lorsque la fonction du back sera dispo
    return new Observable(observer => {
      setTimeout(() => {
        return true;
      }, 1000);
    })

    /* A décommenter lorsque la fonction du back sera dispo
    return this.httpClient.get(BASE_ADDRESS + "???", { params: params });
    */
  }

  isEmailExists(email: string): Observable<Boolean> {
    let params = new HttpParams();

    params = params.append('email', email);

    // TODO
    // A commenter lorsque la fonction du back sera dispo
    return new Observable(observer => {
      setTimeout(() => {
        return true;
      }, 1000);
    })

    /* A décommenter lorsque la fonction du back sera dispo
    return this.httpClient.get(BASE_ADDRESS + "???", { params: params });
    */
  }
}
