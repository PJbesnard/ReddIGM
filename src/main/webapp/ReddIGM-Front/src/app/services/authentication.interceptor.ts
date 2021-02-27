import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from './authentication.service';
import { Router } from '@angular/router';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(private authService: AuthenticationService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    /* If the user is authenticated, we put the JWT token in each request's header */
    if (localStorage.getItem("currentUser")) {
      request = request.clone({
        setHeaders: {
          "Authorization": 'Bearer ' + localStorage.getItem("jwt")
        }
      });
    }

    return next.handle(request).pipe(catchError((response: HttpResponse<any>) => {
      if (response.status === 403) {
        this.router.navigate(["/login"]);
      }
      
      return next.handle(request);
    }))
  }
}
