import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../models/user.model';
import { AlertService } from '../services/alert.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-edit-profile-page',
  templateUrl: './edit-profile-page.component.html',
  styleUrls: ['./edit-profile-page.component.scss']
})
export class EditProfilePageComponent implements OnInit {

  user: User = new User();
  registerForm: FormGroup;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router, private alertService: AlertService) {
    let userId = this.route.snapshot.params['id'];

    this.registerForm = this.formBuilder.group({
      description: '',
      picture: '',
      email: '',
      newsletters: '',
      password: ''
    });

    userService.getUserById(userId).subscribe(
      (response) => {
        this.user = new User().deserialize(response);

        this.registerForm = this.formBuilder.group({
          description: this.user.description,
          picture: this.user.getPicture() === User.defaultPicture ? '' : this.user.getPicture(),
          email: this.user.email,
          newsletters: this.user.newsletterSubscriber,
          password: ''
        });
      }
    );
  }

  ngOnInit(): void {
    // Reset alert
    this.alertService.clear();
  }

  onSubmitForm(): void {
    const formValue = this.registerForm.value;
    let redirecting = false;

    // Reset alert
    this.alertService.clear();

    if ( formValue.description !== this.user.description ) {
      this.user.description = formValue.description;
    }

    if ( formValue.picture !== this.user.getPicture() ) {
      this.user.setPicture(formValue.picture);
    }

    if ( formValue.newsletters !== this.user.newsletterSubscriber ) {
      this.user.newsletterSubscriber = formValue.newsletters;
    }
    
    this.userService.updateUser(this.user.id, this.user).subscribe(
      (response) => {
        if (!redirecting) {
          redirecting = true;
          this.alertService.success("Profile successfully updated", true);
          this.router.navigate(["/users/" + this.user.id]);
        }
      },
      (errorMessage) => {
        this.alertService.error("Updating failed : " + errorMessage);
      }
    );

    if (formValue.password) {
      console.log("Changement du pass");
      this.userService.updateUserPassword(this.user.id, formValue.password).subscribe(
        (response) => {
          if (!redirecting) {
            redirecting = true;
            this.alertService.success("Password successfully updated", true);
            this.router.navigate(["/users/" + this.user.id]);
          }
        },
        (errorMessage) => {
          this.alertService.error("Updating failed");
        }
      );
    }
  }

  onCancelClick(): void {
    this.router.navigate(["/users/" + this.user.id]);
  }
}
