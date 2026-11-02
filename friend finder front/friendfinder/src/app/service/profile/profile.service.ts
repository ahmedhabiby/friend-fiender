import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  doProfile(personalInfo,imageUrl, location, job, workExperience, interest, language): Observable<any> {
    return this.http.post<any>(this.baseUrl + 'saveProfile', {personalInfo,imageUrl, location, job, workExperience, interest, language}).pipe(
      map(response => response)
    )
  }

  getProfile():Observable<any>{
    return this.http.get<any>(this.baseUrl + 'getProfileForLoginUser').pipe(
      map(response => response)
    )
  }
}
