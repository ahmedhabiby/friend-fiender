import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

baseUrl ='http://localhost:8080';
  private token: string;

  constructor(private http: HttpClient) { }
  login(username,password):Observable<any>{

    return this.http.post<any>(this.baseUrl+'/login',{username:username,password:password}).pipe(
      map(response=>response)
    )
  }
  signup(username,password,email):Observable<any>{
    return  this.http.post<any>(this.baseUrl+'/signup',{username:username,password:password,email:email}).pipe(
      map(response=>response)
    )
  }
  isUserLogin():boolean{
    this.token=sessionStorage.getItem('token');
    if(this.token!==undefined&&this.token!==null&&this.token!==" "){
      return true;
    }
    return false;
  }
  logout():void{
    sessionStorage.removeItem('token');
  }
  countAllUser() : Observable<any>{
    return this.http.get<any>(this.baseUrl+'/all/user/registerd',{}).pipe(
      map(response=>response)
    );
  }
  countAllPosts():Observable<any>{
    return this.http.get<any>(this.baseUrl+'/countAllPosts',{}).pipe(
      map(response=>response)
    );
  }
  getLatestPosts():Observable<any>{
    return this.http.get<any>(this.baseUrl+'/latestPost',{}).pipe(
      map(response=>response)
    )
  }
}
