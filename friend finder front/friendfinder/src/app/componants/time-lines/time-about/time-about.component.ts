import { Component, OnInit } from '@angular/core';
import {ProfileService} from '../../../service/profile/profile.service';
import {Profile} from '../../../models/profile/profile';

@Component({
  selector: 'app-time-about',
  templateUrl: './time-about.component.html',
  styleUrls: ['./time-about.component.css']
})
export class TimeAboutComponent implements OnInit {
profile:Profile;
  constructor( private profileService: ProfileService) { }

  ngOnInit(): void {
    this.getProfile();
  }
  getProfile(){
    this.profileService.getProfile().subscribe(
      response => this.profile = response
    )
  }
}
