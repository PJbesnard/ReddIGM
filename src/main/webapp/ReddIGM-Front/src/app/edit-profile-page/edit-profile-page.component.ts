import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-edit-profile-page',
  templateUrl: './edit-profile-page.component.html',
  styleUrls: ['./edit-profile-page.component.scss']
})
export class EditProfilePageComponent implements OnInit {

  @Input()
  username!: string;

  user: User = new User();
  registerForm: FormGroup;

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
    // TODO Récupérer les infos du user
    userService.getUser(this.username).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => {
        console.error(error);
      }
    );

    this.registerForm = this.formBuilder.group({
      description: '',
      picture: '',
      email: '',
      newsletters: '',
      password: ''
    });
  }

  ngOnInit(): void {
  }

  onSubmitForm() {
    const formValue = this.registerForm.value;

    if ( formValue.description !== this.user.description ) {
      // TODO : Mettre à jour formValue.description
    }

    if ( formValue.picture !== this.user.picture ) {
      // TODO : Mettre à jour formValue.picture
    }

    if ( formValue.newsletters !== this.user.newsletters ) {
      // TODO : Mettre à jour formValue.newsletters
    }

    if (formValue.password) {
      // TODO : Mettre à jour formValue.password
    }
  }

}
