import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { ReCaptchaV3Service } from 'ngx-captcha';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  registerForm: FormGroup;
  captchaKey: string = "6LfYkVYaAAAAAH9i5Uz1x7UyraR2OQh11H30Toi3";

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder, private recaptchaV3Service: ReCaptchaV3Service) {
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
    const formValue = this.registerForm.value;

    if (formValue['password'] != formValue['passwordConfirm']) {
      return;
    }

    this.authService.register(
      formValue['username'],
      formValue['password'],
      formValue['email'],
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