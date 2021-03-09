import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from './authentication.service';
import { Router } from '@angular/router';
import { JwtService } from './jwt.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(private authService: AuthenticationService, private jwtService: JwtService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    /* If the user is authenticated, we put the JWT token in each request's header */
    if (this.authService.isLoggedIn()) {
      request = request.clone({
        setHeaders: {
          "Authorization": 'Bearer ' + this.jwtService.getToken()
        }
      });
    }

    return next.handle(request).pipe(catchError((errorResponse: HttpResponse<any>) => {
      if (errorResponse.status === 403) {
        if (this.authService.isLoggedIn()) {
          // Invalid token in memory
          this.authService.logout();
        }

        // Redirecting user to login page
        this.router.navigate(["/login"]);
      }

      return throwError(errorResponse);
    }))
  }
}
