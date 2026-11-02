import { Component, OnInit } from '@angular/core';
import {NotFriendsService} from '../../../service/FriendsNotFollowed/not-friends.service';
import {ProfileService} from '../../../service/profile/profile.service';
import {Profile} from '../../../models/profile/profile';

@Component({
  selector: 'app-left-bar',
  templateUrl: './left-bar.component.html',
  styleUrls: ['./left-bar.component.css']
})
export class LeftBarComponent implements OnInit {
  userName: string;
  numberOfFollowers: number;
  profile:Profile;

  constructor(private notFriendsService :NotFriendsService ,private profileService :ProfileService) { }

  ngOnInit(): void {
    this.aboutUser()
    this.getProfile()
  }
aboutUser(){
    this.notFriendsService.aboutUser().subscribe(response=>{
      this.userName = response.userName;
      this.numberOfFollowers = response.numberOfFollowers;
    })
}
  getProfile(){
    this.profileService.getProfile().subscribe(
      response => this.profile = response
    )
  }
}
