import { PostViewComponent } from './post-view/post-view.component';
import { CardCreatePostComponent } from './card-create-post/card-create-post.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListSubsComponent} from './subs/list-subs/list-subs.component';
import {CreateSubComponent} from './subs/create-sub/create-sub.component'
import {HomePageComponent} from './home-page/home-page.component'
import {PostsFromSubComponent} from './posts-from-sub/posts-from-sub.component'

const routes: Routes = [
  {path:'', component: HomePageComponent},
  {path: 'create-sub', component: CreateSubComponent},
  {path:'posts-from-sub', component: PostsFromSubComponent},
  {path: 'list-subs', component: ListSubsComponent},
  {path: 'home', component: HomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
