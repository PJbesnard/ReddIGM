import { PostViewComponent } from './post-view/post-view.component';
import { CardCreatePostComponent } from './card-create-post/card-create-post.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListSubsComponent} from './subs/list-subs/list-subs.component';
import {CreateSubComponent} from './subs/create-sub/create-sub.component'
import { HomePageComponent } from './home-page/home-page.component';

const routes: Routes = [
  {path: '', component: CreateSubComponent},
  {path: 'list-subs', component: ListSubsComponent},
  {path: 'home', component: HomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
