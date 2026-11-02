import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/internal/operators';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReactionService {
baseUrl = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  doReaction(postId, reaction){
    return this.http.post<any>(this.baseUrl+'saveReaction',{postId,reaction}).pipe(
      map(response => response)
    )
  }
  deleteReaction(postId): Observable<any> {
    return this.http.delete<any>(
      this.baseUrl + 'deleteReaction',
      {
        params: { postId }
      }
    );
  }
  getReaction(postId):Observable<any>{
    return this.http.get<any>(this.baseUrl+'getPostReaction',{params:{postId}}).pipe(
      map(response => response)
    )

  }
}
