import { Component, OnInit } from '@angular/core';
import {NotFriendsService} from '../../service/FriendsNotFollowed/not-friends.service';
import {Requests} from '../../models/Requests/requests';
import {Router} from '@angular/router';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {
  requests:Requests[]=[]
  constructor(private notFriendsService :NotFriendsService ,private router:Router) { }

  ngOnInit(): void {
    this.getAllFriendResponseForLoginUser()
  }
  getAllFriendResponseForLoginUser(){
    this.notFriendsService.getAllFriendResponseForLoginUser().subscribe(response=>{
      this.requests = response;
    })
  }
 acceptRequest(senderId,receiverId){
    this.notFriendsService.acceptRequest(senderId,receiverId).subscribe(response=>{
      this.requests = response;
    })
   this.router.navigateByUrl('requests');

 }
  rejectRequest(senderId,receiverId){
    this.notFriendsService.rejectRequest(senderId,receiverId).subscribe(response=>{
      this.requests = response;
    })
    this.router.navigateByUrl('requests');

  }
}
