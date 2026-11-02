import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class NotFriendsService {

  baseUrl="http://localhost:8080/";
  constructor(private http: HttpClient) { }

  FriendsNotFollowed():Observable<any> {
    return this.http.get(this.baseUrl+"allNotFollowedUsers").pipe(
      map(response=>response)
    )
  }
  addFriend(receiverName):Observable<any>{
    return this.http.post(this.baseUrl+'sendRequest',{receiverName}).pipe(
      map(response=>response)
    )
  }
  aboutUser():Observable<any>{
    return this.http.get(this.baseUrl+'aboutLoginUser').pipe(
      map(response=>response)
    )
  }
  getAllFriendResponseForLoginUser():Observable<any>{
    return this.http.get(this.baseUrl+'getAllFriendResponseForLoginUser').pipe(
      map(response=>response)
    )
  }
  acceptRequest(senderId,receiverId):Observable<any>{
    return this.http.post(
      this.baseUrl + 'acceptRequest?senderId=' + senderId + '&receiverId=' + receiverId,
      null
    );

  }
  rejectRequest(senderId,receiverId):Observable<any>{
    return this.http.post(
      this.baseUrl + 'rejectRequest?senderId=' + senderId + '&receiverId=' + receiverId,
      null
    );
  }
  getAllFriendsForLoginUser():Observable<any>{
    return this.http.get(this.baseUrl+'getAllFriendsForLoginUser').pipe(
      map(response=>response)
    )
  }

}
