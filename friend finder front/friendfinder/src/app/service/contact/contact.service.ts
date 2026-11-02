import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
baseUrl='http://localhost:8080/';
  constructor(private http: HttpClient) { }

  doContact(name,email,phone,message):Observable<any>{
    return this.http.post<any>(this.baseUrl+"saveContact",{name,email,phone,message}).pipe(
      map(response=>response)
    )
  }
}
