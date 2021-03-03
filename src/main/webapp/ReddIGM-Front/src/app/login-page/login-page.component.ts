import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../services/alert.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  registerForm: FormGroup;
  logging: boolean = false;

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder, private router: Router, private alertService: AlertService) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      // User already authenticated, redirecting to home page
      this.router.navigate(["/home"]);
    }
  }

  onSubmitForm() {
    this.logging = true;
    const formValue = this.registerForm.value;

    // Reset alert
    this.alertService.clear();

    this.authService.login(
      formValue['username'],
      formValue['password'],
      () => this.router.navigate(["/home"]),
      (errorMsg) => {
        this.logging = false;
        this.alertService.error("Authentication failed : Invalid username or password");
        this.registerForm.reset();
      }
    );
  }

}
