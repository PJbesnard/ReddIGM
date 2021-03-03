import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private readonly JWT = "reddigm_jwt";
  private readonly CURRENT_USER = "reddigm_currentUser";

  constructor() {
  }

  setToken(token: string): void {
    if (token) {
      localStorage.setItem(this.JWT, token);
    }
  }

  getToken(): string | null {
    return localStorage.getItem(this.JWT);
  }

  removeToken(): void {
    localStorage.removeItem(this.JWT);
  }

  getDecodedToken(): { [key: string]: string } | null {
    const token = localStorage.getItem(this.JWT);

    return token ? jwt_decode(token) : null;
  }

  setUser(user: User): void {
    if (user) {
      localStorage.setItem(this.CURRENT_USER, JSON.stringify(user));
    }
  }

  getUser(): User | null {
    const serializedUser = localStorage.getItem(this.CURRENT_USER);

    return serializedUser ? new User().deserialize(JSON.parse(serializedUser)) : null;
  }

  removeUser(): void {
    localStorage.removeItem(this.CURRENT_USER);
  }

  getExpiryTime(): number | null {
    const decodedToken = this.getDecodedToken();
    
    return decodedToken ? +decodedToken.exp : null;
  }
  
  isTokenExpired(): boolean {
    const expiryTime = this.getExpiryTime();
    
    if (expiryTime) {
      return ((1000 * expiryTime) - (new Date()).getTime()) < 5000;
    } else {
      return false;
    }
  }
}
