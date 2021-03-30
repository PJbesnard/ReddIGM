import { Component, OnInit, Input } from '@angular/core';
import { SubjectService } from '../../services/subject.service';
import { SubjectModel } from '../../models/subject-response';
import { DataService } from '../../services/data.service';
import { Router } from "@angular/router"
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-list-subs',
  templateUrl: './list-subs.component.html',
  styleUrls: ['./list-subs.component.scss']
})
export class ListSubsComponent implements OnInit {
  subjects: Array<SubjectModel> = [];
  message:string = "";
  subscription!: Subscription;
  currentSubjects: Array<SubjectModel> = [];

  constructor(private router: Router, private dataService: DataService, private subjectService: SubjectService) { }

  ngOnInit(): void {
    this.subjectService.getAllSubjects().subscribe(data => {
		this.currentSubjects = data;
		this.subjects = data;
	});
	this.subscription = this.dataService.currentMessage.subscribe(message => this.search(message));
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  showSub(id?: number): void {
    this.router.navigateByUrl('/posts-from-sub/'+id);
  }

  search(searchText : string){
	if (!this.subjects) {
		this.currentSubjects = [];
		console.log(this.currentSubjects)
	}
	  if (!searchText) {
		this.currentSubjects = this.subjects;
		console.log(this.currentSubjects)
	  }
	  searchText = searchText.toLocaleLowerCase();

	  this.currentSubjects = this.subjects.filter(it => {
		return it.name.toLocaleLowerCase().includes(searchText);

	  })
  }


}
