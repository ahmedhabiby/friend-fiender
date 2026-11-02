import { Component, OnInit } from '@angular/core';
import {NotFriendsService} from '../../../service/FriendsNotFollowed/not-friends.service';
import {Friends} from '../../../models/friends/friends';

@Component({
  selector: 'app-time-friends',
  templateUrl: './time-friends.component.html',
  styleUrls: ['./time-friends.component.css']
})
export class TimeFriendsComponent implements OnInit {
  friends:Friends[]=[]
  constructor(private notFriendsService :NotFriendsService) { }

  ngOnInit(): void {
    this.getAllFriendsForLoginUser()
  }
  getAllFriendsForLoginUser(){
    this.notFriendsService.getAllFriendsForLoginUser().subscribe(
      result=>{
        this.friends = result;
      }
    )
  }

}
