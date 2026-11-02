import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/internal/operators';
import {Comments} from "../../models/comment/comments";

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  baseUrl = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  doComment(content,postId):Observable<any>{
    return  this.http.post<any>(this.baseUrl+'saveComment', {content,postId}).pipe(
      map(response => response)
    )
  }
  getLatestCommentForLoginUser():Observable<any>{
    return this.http.get<any>(this.baseUrl+'searchLatestCommentForLoginUser').pipe(
      map(response => response)
    )
  }
  updateComment(comment: Comments) {
    return this.http.put<any>(
      this.baseUrl + 'updateComment',
      comment
    );
  }

  deleteComment(comment_id){
    return this.http.delete<any>(this.baseUrl+'deleteComment',{params:{comment_id}}).pipe(
      map(response => response)
    )
  }
}
