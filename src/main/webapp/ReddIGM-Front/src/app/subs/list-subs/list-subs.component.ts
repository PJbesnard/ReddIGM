import { Component, OnInit, Input } from '@angular/core';
import { SubjectService } from '../../services/subject.service';
import { SubjectModel } from '../../models/subject-response';

@Component({
  selector: 'app-list-subs',
  templateUrl: './list-subs.component.html',
  styleUrls: ['./list-subs.component.scss']
})
export class ListSubsComponent implements OnInit {
  subjects: Array<SubjectModel> = [];

  constructor(private subjectService: SubjectService) { }

  ngOnInit(): void {
    this.subjectService.getAllSubjects().subscribe(data =>this.subjects = data);
  }
}
