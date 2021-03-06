import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-edit-profile-page',
  templateUrl: './edit-profile-page.component.html',
  styleUrls: ['./edit-profile-page.component.scss']
})
export class EditProfilePageComponent implements OnInit {

  user: User = new User();
  registerForm: FormGroup;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private route: ActivatedRoute) {
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
  }

  onSubmitForm() {
    const formValue = this.registerForm.value;

    if ( formValue.description !== this.user.description ) {
      // TODO : Mettre à jour formValue.description
    }

    if ( formValue.picture !== this.user.getPicture() ) {
      // TODO : Mettre à jour formValue.picture
    }

    if ( formValue.newsletters !== this.user.newsletterSubscriber ) {
      // TODO : Mettre à jour formValue.newsletters
    }

    if (formValue.password) {
      // TODO : Mettre à jour formValue.password
    }
  }

}
