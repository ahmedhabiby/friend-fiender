import { Component, OnInit } from '@angular/core';
import { Post } from '../../../models/post/post';
import { PostService } from '../../../service/post/post.service';

@Component({
  selector: 'app-time-album',
  templateUrl: './time-album.component.html',
  styleUrls: ['./time-album.component.css']
})
export class TimeAlbumComponent implements OnInit {

  posts: Post[] = [];
  images: string[] = [];

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.loadImages();
  }

  loadImages(): void {
    this.postService.getAllPostForLoginUser().subscribe({
      next: (posts: Post[]) => {
        this.posts = posts;
        this.images = [];

        posts.forEach(post => {
          if (post.mediaUrl && post.mediaUrl.length > 0) {
            post.mediaUrl.forEach(media => {
              if (this.isImage(media)) {
                this.images.push(media);
              }
            });
          }
        });
      },
      error: err => {
        console.error(err);
      }
    });
  }

  isImage(file: string | null | undefined): boolean {
    if (!file) {
      return false;
    }

    const extension = file.toLowerCase();

    return (
      extension.endsWith('.jpg') ||
      extension.endsWith('.jpeg') ||
      extension.endsWith('.png') ||
      extension.endsWith('.gif') ||
      extension.endsWith('.webp')
    );
  }

  getImagePath(image: string): string {
    return 'assets/images/post-images/' + image;
  }
}
