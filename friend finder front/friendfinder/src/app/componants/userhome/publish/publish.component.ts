import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { PostService } from '../../../service/post/post.service';
import { Router } from '@angular/router';
import {th} from 'date-fns/locale';
import {ProfileService} from '../../../service/profile/profile.service';
import {Profile} from '../../../models/profile/profile';

@Component({
  selector: 'app-publish',
  templateUrl: './publish.component.html',
  styleUrls: ['./publish.component.css']
})
export class PublishComponent implements OnInit {
  @ViewChild('content') content!: ElementRef<HTMLTextAreaElement>;
  @ViewChild('img') img!: ElementRef<HTMLInputElement>;
  @ViewChild('video') video!: ElementRef<HTMLInputElement>;

  message = '';
  success = false;
  profile:Profile;

  constructor(private postService: PostService, private router: Router,private profileService: ProfileService) { }

  ngOnInit(): void {
    this.getProfile()
  }



  doPost(content: HTMLTextAreaElement,
         img: HTMLInputElement,
         video: HTMLInputElement) {

    let fileName = '';
    let type = '';

    if (img.value) {
      fileName = img.value.substring(img.value.lastIndexOf('\\') + 1);
      type = 'image';
    } else if (video.value) {
      fileName = video.value.substring(video.value.lastIndexOf('\\') + 1);
      type = 'video';
    }

    this.postService.savePost(content.value, fileName, type)
      .subscribe({

        next: () => {

          this.success = true;
          this.message = '✅ Post created successfully.';

          content.value = '';
          img.value = '';
          video.value = '';


          setTimeout(() => {
            this.message = '';
          }, 3000);
          window.location.reload();
        },

        error: () => {

          this.success = false;
          this.message = '❌ Failed to create the post.';

          setTimeout(() => {
            this.message = '';
          }, 3000);

        }

      });

  }
  getProfile(){
    this.profileService.getProfile().subscribe(
      response => this.profile = response
    )
  }


}
