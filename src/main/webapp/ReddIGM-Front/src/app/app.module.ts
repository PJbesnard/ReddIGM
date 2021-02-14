import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule  } from '@angular/forms';
import { NgxCaptchaModule } from 'ngx-captcha';

import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { PostViewComponent } from './post-view/post-view.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { CommentPreviewComponent } from './comment-preview/comment-preview.component';

import { AuthenticationService } from './services/authentication.service';
import { EditProfilePageComponent } from './edit-profile-page/edit-profile-page.component'

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    PostViewComponent,
    ProfilePageComponent,
    CommentPreviewComponent,
    EditProfilePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule
    HttpClientModule,
    ReactiveFormsModule,
    NgxCaptchaModule
  ],
  providers: [
    AuthenticationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
