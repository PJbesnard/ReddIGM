import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { PostViewComponent } from './post-view/post-view.component';
import { TitledCardButtonComponent } from './titled-card-button/titled-card-button.component';
import { PostViewInContextComponent } from './post-view-in-context/post-view-in-context.component';
import { CardCreatePostComponent } from './card-create-post/card-create-post.component';
import { CKEditorModule } from 'ckeditor4-angular';
import { CreatePostComponent } from './create-post/create-post.component';

import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    PostViewComponent,
    TitledCardButtonComponent,
    PostViewInContextComponent,
    CardCreatePostComponent,
    CreatePostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    CKEditorModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
