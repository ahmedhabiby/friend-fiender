import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ProfileService} from '../../service/profile/profile.service';
import {Profile} from '../../models/profile/profile';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile:Profile;
  messageEn=''
  messageAr=''
  constructor(private router: Router, private profileService: ProfileService) { }

  ngOnInit(): void {
  }

  doProfile(personalInfo,imageUrl, location, job, workExperience, interest, language) {
    if (!this.validateInput(personalInfo,imageUrl, location, job, workExperience, interest, language)) {
      setTimeout(() => {
        this.messageAr = "";
        this.messageEn = "";
      }, 3000);
      return;
    }
      this.profileService.doProfile(personalInfo,imageUrl, location, job, workExperience, interest, language).subscribe(
        response => {
            this.router.navigateByUrl("/mainPage")
        },
        error => {
          console.log(error);
          this.messageAr =error.error.messageAr;
          this.messageEn =error.error.messageEn;
          setTimeout(()=>{
            this.messageAr="";
            this.messageEn="";
          },3000)
        }
      )
    }

getProfile(){
    this.profileService.getProfile().subscribe(
      response => this.profile = response
    )
}

  validateInput(personalInfo,imageUrl, location, job, workExperience, interest, language): boolean{
    if(!personalInfo){
      this.messageAr='المعلومات لا يجب ان يكون فارغ';
      this.messageEn='personalInfo must not be empty';
      return false;
    }
    if(!imageUrl){
      this.messageAr='الصورة لا يجب ان يكون فارغ';
      this.messageEn='imageUrl must not be empty';
      return false;
    }
    if(!location){
      this.messageAr='موقعك لا يجب ان يكون فارغ'
      this.messageEn='location must not be empty';
      return false;
    }
    if(!job){
      this.messageAr='الوظيفة لا يجب ان يكون فارغ';
      this.messageEn='job  must not be empty';
      return false;
    }
    if(!workExperience){
      this.messageAr='الخبرة لا يجب ان يكون فارغ';
      this.messageEn='workExperience must not be empty';
      return false;
    }
    if(!interest){
      this.messageAr='الاهتمامات لا يجب ان يكون فارغ';
      this.messageEn='interest must not be empty';
      return false;
    }
    if(!language){
      this.messageAr='اللغة لا يجب ان يكون فارغ';
      this.messageEn='language must not be empty';
      return false;
    }

    return true;
  }
}
