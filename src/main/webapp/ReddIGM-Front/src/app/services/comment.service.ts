import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Comment } from '../models/comment.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VoteType } from '../models/vote-type.enum';
import { CreateCommentPayload } from '../create-comment/create-comment.payload';



const BASE_ADDRESS = environment.baseURL;

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private urlComment: string;
  private urlPost: string;
  private urlUser: string;

  constructor(private http: HttpClient, private httpClient: HttpClient) {
    this.urlComment = BASE_ADDRESS + 'comments/comment/';
    this.urlPost = BASE_ADDRESS + 'comments/post/';
    this.urlUser = BASE_ADDRESS + 'comments/user/';
  }

  getCommentsFromPost(sort: String, id: number): Observable<Array<Comment>> {
    return this.http.get<Array<Comment>>(this.urlPost + id + '/' + sort, {
      headers: new HttpHeaders(
        {
          'Access-Control-Allow-Origin':'*'
        }
      )
    });

  }

  deleteComment(id: number): Observable<Array<Comment>> {
    return this.http.get<Array<Comment>>(BASE_ADDRESS + "comments/delete/" + id, {
      headers: new HttpHeaders(
        {
          'Access-Control-Allow-Origin':'*'
        }
      )
    });

  }

  getCommentsForComment(sort: String, id: number): Observable<Array<Comment>> {
    return this.http.get<Array<Comment>>(this.urlComment + id + '/' + sort, {
      headers: new HttpHeaders(
        {
          'Access-Control-Allow-Origin':'*'
        }
      )
    });
  }

  getCommentsByUsername(username: string): Observable<Array<Comment>> {
    return this.http.get<Array<Comment>>(this.urlUser + username);
  }

  createComment(commentPayload: CreateCommentPayload): Observable<any> {
    return this.httpClient.post(BASE_ADDRESS+"comments/", commentPayload);
  }

  vote(commentId: number, vote: VoteType): Observable<Comment> {
    const body = {
      commentId: commentId,
      vote: vote
    };
    return this.httpClient.post<Comment>(BASE_ADDRESS+"comments/vote", body);
  }
}
