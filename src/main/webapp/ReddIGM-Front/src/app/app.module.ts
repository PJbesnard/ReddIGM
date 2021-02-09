import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { from } from 'rxjs';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreatePostComponent } from './pages/modals/create-post/create-post.component';
import { CKEditorModule } from 'ckeditor4-angular';
import { CardCreatePostComponent } from './card-create-post/card-create-post.component';
import { BrowseSubsComponent } from './browse-subs/browse-subs.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { PostViewComponent } from './post-view/post-view.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    AppComponent,
    CreatePostComponent,
    CardCreatePostComponent,
    BrowseSubsComponent,
    NavBarComponent,
    PostViewComponent
  ],
  imports: [
    BrowserModule,
	AppRoutingModule,
	CKEditorModule,
  FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
