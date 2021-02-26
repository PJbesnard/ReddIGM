import { PostViewComponent } from './post-view/post-view.component';
import { CardCreatePostComponent } from './card-create-post/card-create-post.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { BrowseSubsComponent } from './browse-subs/browse-subs.component';

const routes: Routes = [
	{ path: 'test', component: CardCreatePostComponent },
	{ path: 'nav-bar', component: NavBarComponent },
	{ path: 'browse-sub', component: BrowseSubsComponent },
	{ path: 'testpost', component: PostViewComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
