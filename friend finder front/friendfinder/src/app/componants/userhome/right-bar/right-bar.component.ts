import { Component, OnInit } from '@angular/core';
import {NotFriendsService} from '../../../service/FriendsNotFollowed/not-friends.service';
import {Router} from '@angular/router';
import {Notfriends} from '../../../models/NotFriends/notfriends';

@Component({
  selector: 'app-right-bar',
  templateUrl: './right-bar.component.html',
  styleUrls: ['./right-bar.component.css']
})
export class RightBarComponent implements OnInit {

  friends!: Notfriends;
  constructor(private notFriendsService: NotFriendsService, private router: Router) {
  }

  ngOnInit(): void {
    this.FriendsNotFollowed()
  }

  FriendsNotFollowed() {
    this.notFriendsService.FriendsNotFollowed().subscribe(
      result => {
        this.friends = result;
      }
    )
  }
  addFriend(receiverName){
    this.notFriendsService.addFriend(receiverName).subscribe(
      result => result
    )
}

}

