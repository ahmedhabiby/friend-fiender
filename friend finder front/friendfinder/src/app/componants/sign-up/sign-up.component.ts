import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {SecurityService} from '../../service/security/security.service';
import {Post} from "../../models/post/post";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  messageAr=''
  messageEn=''
  numOfPosts = 0;
  numOfUsers = 0;
  posts:Post[] = [];
  constructor(private router: Router, private securityService :SecurityService) { }

  ngOnInit(): void {
    this.countAllPosts();
    this.countAllRegisterdUsers();
    this.getLatestPosts();
  }

  signup(username,email, password ){
    debugger
    if(!this.validateInput(username,email,password)){
      setTimeout(() => {
        this.messageAr = "";
        this.messageEn = "";
      }, 3000);
      return;
    }
    this.securityService.signup(username, password, email).subscribe(
      response=>{

        sessionStorage.setItem("token",response.token);
        this.router.navigateByUrl("/login");
      },
      error => {
        console.log(error);

        if (Array.isArray(error.error)) {
          this.messageAr = error.error[0]?.messageAr || "";
          this.messageEn = error.error[0]?.messageEn || "";
        } else {
          this.messageAr = error.error?.messageAr || "";
          this.messageEn = error.error?.messageEn || "";
        }

        setTimeout(() => {
          this.messageEn = "";
          this.messageAr = "";
        }, 3000);
      }
    )
  }
  validateInput(username,email,password): boolean{
    if(!username){
      this.messageAr='الاسم لا يجب ان يكون فارغ';
      this.messageEn='userName must not be empty';
      return false;
    }
    if(!email){
      this.messageAr='الايميل لا يجب ان يكون فارغ'
      this.messageEn='Email must not be empty';
      return false;
    }
    if(!password){
      this.messageAr='كلمة المرور لا يجب ان يكون فارغ';
      this.messageEn='Password must not be empty';
      return false;
    }

    return true;
  }
  countAllRegisterdUsers(){
    this.securityService.countAllUser().subscribe(
      response=>{
        this.numOfUsers = response;
      }
    )
  }
  countAllPosts(){
    this.securityService.countAllPosts().subscribe(
      response=>{
        this.numOfPosts = response;
      }
    )
  }
  getLatestPosts(){
    this.securityService.getLatestPosts().subscribe(
      response=>{
        this.posts = response;
      }
    )
  }
  timeAgo(date: string): string {
    const safeDate = date.substring(0, 19); // 2026-02-01T18:54:30
    const past = new Date(safeDate).getTime();
    const now = Date.now();

    const diff = Math.floor((now - past) / 1000);

    if (diff < 60) { return 'just now'; }
    const min = Math.floor(diff / 60);
    if (min < 60) { return `${min} min ago`; }
    const h = Math.floor(min / 60);
    if (h < 24) { return `${h} h ago`; }
    const d = Math.floor(h / 24);
    return `${d} d ago`;
  }
}
