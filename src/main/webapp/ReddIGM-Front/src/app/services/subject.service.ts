import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SubjectModel } from '../models/subject-response';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/subjects/'
  }

  getAllSubjects(): Observable<Array<SubjectModel>> {
    return this.http.get<Array<SubjectModel>>(this.url, {
      headers: new HttpHeaders(
        {
          'Access-Control-Allow-Origin':'*'
        }
      )
    });
  }

  getSubject(id: number): Observable<SubjectModel> {
    return this.http.get<SubjectModel>(this.url + id, {
      headers: new HttpHeaders(
        {
          'Access-Control-Allow-Origin':'*'
        }
      )
    });
  }

  createSubject(subjectModel: SubjectModel): Observable<SubjectModel> {
    return this.http.post<SubjectModel>(this.url,
      subjectModel);
  }
}
