import { Component, OnInit } from '@angular/core';
import {ProfileService} from '../../../service/profile/profile.service';
import {Profile} from '../../../models/profile/profile';
import {NotFriendsService} from '../../../service/FriendsNotFollowed/not-friends.service';

@Component({
  selector: 'app-time-line-profile',
  templateUrl: './time-line-profile.component.html',
  styleUrls: ['./time-line-profile.component.css']
})
export class TimeLineProfileComponent implements OnInit {
  profile:Profile;
  userName: string;
  numberOfFollowers: number;
  constructor(private profileService: ProfileService,private notFriendsService :NotFriendsService) { }

  ngOnInit(): void {
    this.getProfile()
    this.aboutUser()
  }
  getProfile(){
    this.profileService.getProfile().subscribe(
      response => this.profile = response
    )
  }
  aboutUser(){
    this.notFriendsService.aboutUser().subscribe(response=>{
      this.userName = response.userName;
      this.numberOfFollowers = response.numberOfFollowers;
    })
  }
}
