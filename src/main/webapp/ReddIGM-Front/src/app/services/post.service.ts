import { environment } from './../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostModel } from '../models/post.model';
import { CreatePostPayload } from '../pages/modals/create-post/create-post.payload';
import { VoteType } from '../models/vote-type.enum';


const BASE_ADDRESS = environment.baseURL;

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient ) { }

  getPost(id: string): Observable<PostModel> {
    return this.httpClient.get<PostModel>(BASE_ADDRESS+"posts/"+id);
  }

  getAllPosts(): Observable<PostModel[]> {
    return this.httpClient.get<PostModel[]>(BASE_ADDRESS+"posts/");
  }

  getPostsByUserId(id: number): Observable<PostModel[]> {
    return this.httpClient.get<PostModel[]>(BASE_ADDRESS+"posts/by-user/" + id);
  }

  getPostsBySubject(id: number): Observable<PostModel[]> {
    return this.httpClient.get<PostModel[]>(BASE_ADDRESS+"posts/by-subject/" + id);
  }

  createPost(postPayload: CreatePostPayload): Observable<any> {
    return this.httpClient.post(BASE_ADDRESS+"posts/", postPayload);
  }

  vote(postId: number, vote: VoteType): Observable<PostModel> {
    const body = {
      postId: postId,
      vote: vote
    };
    return this.httpClient.post<PostModel>(BASE_ADDRESS+"posts/vote", body);
  }

}
