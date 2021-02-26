import { Component, OnInit } from '@angular/core';
import {SubjectService} from "../subject.service";
import {SubjectModel} from "../subject-response";
import { throwError } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-sub',
  templateUrl: './create-sub.component.html',
  styleUrls: ['./create-sub.component.scss']
})
export class CreateSubComponent implements OnInit {

  constructor(private router: Router, private subjectService: SubjectService) {

  }

  ngOnInit(): void {
  }

  create(): void {
    console.log("wesh alors");
    let subName = (<HTMLInputElement>document.getElementById('subName')).value;
    let subDesc = (<HTMLInputElement>document.getElementById('subDesc')).value;
    let subjectModel: SubjectModel = {
      name: subName,
      description: subDesc,
      posts: []
    }
    this.subjectService.createSubject(subjectModel).subscribe(data => {
      this.router.navigateByUrl('/list-subs');
    }, error => {
      throwError(error);
    })
  }

}
