import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListSubsComponent} from './subs/list-subs/list-subs.component';
import {CreateSubComponent} from './subs/create-sub/create-sub.component'

const routes: Routes = [
  {path: '', component: CreateSubComponent},
  {path: 'list-subs', component: ListSubsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
