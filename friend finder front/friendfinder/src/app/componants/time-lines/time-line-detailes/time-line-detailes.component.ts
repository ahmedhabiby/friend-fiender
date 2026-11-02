import { Component, OnInit } from '@angular/core';
import {NotFriendsService} from '../../../service/FriendsNotFollowed/not-friends.service';
import {CommentService} from '../../../service/comment/comment.service';
import {Comments} from '../../../models/comment/comments';
import {formatDistanceToNow} from 'date-fns';
import {PostService} from '../../../service/post/post.service';

@Component({
  selector: 'app-time-line-detailes',
  templateUrl: './time-line-detailes.component.html',
  styleUrls: ['./time-line-detailes.component.css']
})
export class TimeLineDetailesComponent implements OnInit {
  userName: string;
  comments: Comments;
  createdAt:string;
  createdAt1:string;
  constructor(private notFriendsService :NotFriendsService,private postService:PostService,private commentService:CommentService) { }

  ngOnInit(): void {
    this.aboutUser()
    this.getLatestComment()
    this.getLatestCommentForLoginUser()
    this.getLatestPostReactionForLoginUser()
  }
  aboutUser(){
    this.notFriendsService.aboutUser().subscribe(response=>{
      this.userName = response.userName;
    })
  }
  getLatestComment(){
  this.commentService.getLatestCommentForLoginUser().subscribe(response=>{
    this.comments = response;
  })
  }
  timeAgo(date?: string): string {
    if (!date) {
      return '';
    }

    const safeDate = date.substring(0, 19);
    const past = new Date(safeDate).getTime();
    const now = Date.now();

    const diff = Math.floor((now - past) / 1000);

    if (diff < 60) return 'just now';

    const min = Math.floor(diff / 60);
    if (min < 60) return `${min} min ago`;

    const h = Math.floor(min / 60);
    if (h < 24) return `${h} h ago`;

    const d = Math.floor(h / 24);
    return `${d} d ago`;
  }

  getLatestCommentForLoginUser(){
    this.postService.getLatestPostForLoginUser().subscribe(response=>{
      this.createdAt = response.createdAt;
    })
  }
  getLatestPostReactionForLoginUser(){
    this.postService.getLatestPostReactionForLoginUser().subscribe(response=>{
      this.createdAt1 = response.createdAt;
    })
  }


}
