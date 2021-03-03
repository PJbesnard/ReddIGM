import { Component, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ReCaptcha2Component } from 'ngx-captcha';
import { AlertService } from '../services/alert.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {
  @ViewChild('captchaElem')
  captchaElem!: ReCaptcha2Component;

  registerForm: FormGroup;
  captchaKey: string = "6LfYkVYaAAAAAH9i5Uz1x7UyraR2OQh11H30Toi3";
  logging: boolean = false;

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder, private router: Router, private alertService: AlertService) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      passwordConfirm: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      subscribeNews: '',
      recaptcha: ['', Validators.required]
    }, {
      validators: passwordConfirmValidator()
    });
  }

  ngOnInit(): void { }

  onSubmitForm() {
    this.logging = true;
    const formValue = this.registerForm.value;

    // Reset alert
    this.alertService.clear();

    this.authService.register(
      formValue['username'],
      formValue['password'],
      formValue['email'],
      () => this.router.navigate(["/home"]),
      (errorMsg) => {
        this.logging = false;
        this.alertService.error(errorMsg);
        this.registerForm.reset();
        this.captchaElem.resetCaptcha();
      }
    );
  }
}

export function passwordConfirmValidator(): ValidatorFn {  
  return (control: AbstractControl): { [key: string]: boolean } | null => {
    const password: string = control.get('password')!.value;
    const confirmPassword: string = control.get('passwordConfirm')!.value;

    return password !== confirmPassword ? {passwordConfirmValidator: true} : null;
  }
}