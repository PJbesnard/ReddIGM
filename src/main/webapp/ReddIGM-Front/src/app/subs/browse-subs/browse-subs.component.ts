import { Component, OnInit } from '@angular/core';
import { SubjectService } from '../subject.service';
import { SubjectModel } from '../subject-response';

@Component({
  selector: 'app-browse-subs',
  templateUrl: './browse-subs.component.html',
  styleUrls: ['./browse-subs.component.scss']
})
export class BrowseSubsComponent implements OnInit {
  subjects: Array<SubjectModel> = [];
  viewAll: boolean = false;
  constructor(private subjectService: SubjectService) {
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
}
