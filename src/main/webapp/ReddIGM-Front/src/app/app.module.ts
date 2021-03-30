import { DataService } from './services/data.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { NgxCaptchaModule } from 'ngx-captcha';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { AppComponent } from './app.component';
import { CreatePostComponent } from './pages/modals/create-post/create-post.component';
import { CKEditorModule } from 'ckeditor4-angular';
import { CardCreatePostComponent } from './card-create-post/card-create-post.component';
import { BrowseSubsComponent } from './subs/browse-subs/browse-subs.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { PostViewComponent } from './post-view/post-view.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { CommentPreviewComponent } from './comment-preview/comment-preview.component';
import { EditProfilePageComponent } from './edit-profile-page/edit-profile-page.component'

import { AuthenticationService } from './services/authentication.service';
import { ListSubsComponent } from './subs/list-subs/list-subs.component';
import { CardCreateSubComponent } from './subs/card-create-sub/card-create-sub.component';
import { CreateSubComponent } from './subs/create-sub/create-sub.component';
import { HomePageComponent } from './home-page/home-page.component';
import { PostsFromSubComponent } from './posts-from-sub/posts-from-sub.component';
import { AuthenticationInterceptor } from './services/authentication.interceptor';
import { DisplayPostPageComponent } from './display-post-page/display-post-page.component';
import { PostViewInContextComponent } from './post-view-in-context/post-view-in-context.component';
import { AlertComponent } from './alert/alert.component';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';
import { CreateCommentComponent } from './create-comment/create-comment.component';
import { PostPreviewComponent } from './post-preview/post-preview.component';

@NgModule({
  declarations: [
    AppComponent,
    CreatePostComponent,
    CardCreatePostComponent,
    BrowseSubsComponent,
    NavBarComponent,
    PostViewComponent,
    LoginPageComponent,
    RegisterPageComponent,
    PostViewComponent,
    ProfilePageComponent,
    CommentPreviewComponent,
    EditProfilePageComponent,
    ListSubsComponent,
    CardCreateSubComponent,
    CreateSubComponent,
    HomePageComponent,
    PostsFromSubComponent,
    DisplayPostPageComponent,
    PostViewInContextComponent,
    AlertComponent,
    NotFoundPageComponent,
    CreateCommentComponent,
    PostPreviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CKEditorModule,
    FontAwesomeModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxCaptchaModule,
	  FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true },
    AuthenticationService,
    DataService,
    ListSubsComponent,
    CardCreateSubComponent,
    CreateSubComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
