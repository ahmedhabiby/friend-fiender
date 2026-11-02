import { Injectable } from '@angular/core';

import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {SecurityService} from '../security/security.service';

@Injectable()
export class InterceptorInterceptor implements HttpInterceptor {

  constructor(private securityService :SecurityService) {}
token: string;
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    this.token=sessionStorage.getItem('token');
    if(this.securityService.isUserLogin()){
      request=request.clone({
        setHeaders: {
          Authorization:"Bearer "+this.token
        }
      });
    }
    return next.handle(request);
  }
}
