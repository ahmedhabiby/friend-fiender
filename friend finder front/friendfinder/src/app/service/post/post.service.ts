import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  baseUrl="http://localhost:8080/";

  constructor(private http: HttpClient) { }

  getAllPosts():Observable<any>{
    return this.http.get<any>(this.baseUrl+'getAllPost').pipe(
      map(response => response)
    )
  }
  getAllPostForLoginUser():Observable<any>{
    return this.http.get<any>(this.baseUrl+'getAllPostForLoginUser').pipe(
      map(response => response)
    )
  }
  savePost(content,mediaUrl,mediaType):Observable<any>{
    debugger
    return this.http.post<any>(this.baseUrl+'savePost',{content,mediaUrl,mediaType}).pipe(
      map(response => response)
    )
  }
  getLatestPostForLoginUser():Observable<any>{
    return this.http.get<any>(this.baseUrl+'getLatestPostForLoginUser').pipe(
      map(response => response)
    )
  }
  getLatestPostReactionForLoginUser():Observable<any>{
    return this.http.get<any>(this.baseUrl+'getLatestPostReactionForLoginUser').pipe(
      map(response => response)
    )
  }

}
