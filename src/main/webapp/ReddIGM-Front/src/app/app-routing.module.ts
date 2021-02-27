import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListSubsComponent} from './subs/list-subs/list-subs.component';
import {CreateSubComponent} from './subs/create-sub/create-sub.component'
import {HomePageComponent} from './home-page/home-page.component'
import {PostsFromSubComponent} from './posts-from-sub/posts-from-sub.component'
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { EditProfilePageComponent } from './edit-profile-page/edit-profile-page.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomePageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'register', component: RegisterPageComponent},
  {path: 'users/:id', component: ProfilePageComponent},
  {path: 'users/:id/edit', component: EditProfilePageComponent, canActivate: [AuthGuardService]},
  {path: 'create-sub', component: CreateSubComponent, canActivate: [AuthGuardService]},
  {path: 'posts-from-sub', component: PostsFromSubComponent},
  {path: 'list-subs', component: ListSubsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
