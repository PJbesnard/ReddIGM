import { Component, OnInit } from '@angular/core';
import { SubjectService } from '../../services/subject.service';
import { SubjectModel } from '../../models/subject-response';
import { Router } from "@angular/router"

@Component({
  selector: 'app-browse-subs',
  templateUrl: './browse-subs.component.html',
  styleUrls: ['./browse-subs.component.scss']
})
export class BrowseSubsComponent implements OnInit {
  subjects: Array<SubjectModel> = [];
  viewAll: boolean = false;
  constructor(private router: Router, private subjectService: SubjectService) {
  }

  ngOnInit(): void {
    this.subjectService.getAllSubjects().subscribe(data =>{
      if (data.length > 5) {
        this.subjects = data.splice(0, 5);
        this.viewAll = true;
      } else {
        this.subjects = data;
      }
    });
  }

  showSub(id?: number): void {
    this.router.navigateByUrl('/posts-from-sub/'+id);
  }

  viewAllClick(): void {
    this.router.navigateByUrl('/list-subs');
  }
}
