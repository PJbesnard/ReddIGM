import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { from } from 'rxjs';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreatePostComponent } from './pages/modals/create-post/create-post.component';
import { CKEditorModule } from 'ckeditor4-angular';
import { CardCreatePostComponent } from './card-create-post/card-create-post.component';
import { BrowseSubsComponent } from './browse-subs/browse-subs.component';

@NgModule({
  declarations: [
    AppComponent,
    CreatePostComponent,
    CardCreatePostComponent,
    BrowseSubsComponent,
  ],
  imports: [
    BrowserModule,
	AppRoutingModule,
	CKEditorModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
