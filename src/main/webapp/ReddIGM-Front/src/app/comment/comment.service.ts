import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommentModel } from './comment-response';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private urlComment: string;
  private urlPost: string;


  constructor(private http: HttpClient) {
    this.urlComment = 'http://localhost:8080/comments/comment/'
    this.urlPost = 'http://localhost:8080/comments/post/'
  }

  getCommentsFromPost(id: number): Observable<Array<CommentModel>> {
    return this.http.get<Array<CommentModel>>(this.urlPost + id, {
      headers: new HttpHeaders(
        {
          'Access-Control-Allow-Origin':'*'
        }
      )
    });

  }

  getCommentsForComment(id: number): Observable<Array<CommentModel>> {
    return this.http.get<Array<CommentModel>>(this.urlComment + id, {
      headers: new HttpHeaders(
        {
          'Access-Control-Allow-Origin':'*'
        }
      )
    });

  }
}
