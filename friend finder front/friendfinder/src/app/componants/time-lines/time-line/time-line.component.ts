import { Component, OnInit } from '@angular/core';
import {PostService} from '../../../service/post/post.service';
import {Post} from '../../../models/post/post';
import {ProfileService} from '../../../service/profile/profile.service';
import {Profile} from '../../../models/profile/profile';

@Component({
  selector: 'app-time-line',
  templateUrl: './time-line.component.html',
  styleUrls: ['./time-line.component.css']
})
export class TimeLineComponent implements OnInit {
  posts:Post[]=[];
  profile:Profile;

  constructor(private postService :PostService,private profileService: ProfileService) { }

  ngOnInit(): void {
    this.getAllPostForLoginUser()
    this.getProfile()

  }
  getAllPostForLoginUser(){
    this.postService.getAllPostForLoginUser().subscribe(
      posts => this.posts = posts
    )
  }
  getProfile(){
    this.profileService.getProfile().subscribe(
      response => this.profile = response
    )
  }
  isImage(file: string): boolean {
    if (!file) {
      return false;
    }

    const extension = file.split('.').pop()?.toLowerCase();

    return [
      'jpg',
      'jpeg',
      'png',
      'gif',
      'bmp',
      'webp',
      'svg'
    ].includes(extension!);
  }

  isVideo(file: string): boolean {
    if (!file) {
      return false;
    }

    const extension = file.split('.').pop()?.toLowerCase();

    return [
      'mp4',
      'webm',
      'ogg',
      'mov',
      'avi',
      'mkv'
    ].includes(extension!);
  }
}
