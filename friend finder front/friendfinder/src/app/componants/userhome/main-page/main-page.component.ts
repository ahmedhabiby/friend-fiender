import { Component, OnInit } from '@angular/core';
import {PostService} from '../../../service/post/post.service';
import {Post} from '../../../models/post/post';
import {CommentService} from '../../../service/comment/comment.service';
import {ReactionService} from '../../../service/reaction/reaction.service';
import {ProfileService} from '../../../service/profile/profile.service';
import {Profile} from '../../../models/profile/profile';
import {Postreaction} from "../../../models/postReaction/postreaction";
import {NotFriendsService} from "../../../service/FriendsNotFollowed/not-friends.service";
import {Friends} from "../../../models/friends/friends";
import {Comments} from "../../../models/comment/comments";
import {SearchService} from "../../../service/search/search.service";


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private postService :PostService, private profileService: ProfileService,
              private commentService: CommentService, private reactionService: ReactionService,
              private notFriends: NotFriendsService,
              private _searchService: SearchService) { }
  profile: Profile;
  reaction: Postreaction;
  friends: Friends[]=[];
  currentUserId:string;
  searchText: string = '';
  posts: Post[] = [];
  filteredPosts: Post[] = [];

  ngOnInit(): void {

    this.getAllPosts();
    this.getProfile();
    this.getAllFriends();

    this._searchService.search$.subscribe(value => {
      this.filterPosts(value);
    });
  }

  getAllPosts(): void {

    this.postService.getAllPosts().subscribe(
      response => {

        this.posts = response;

        // Apply current search again after loading posts
        this.filterPosts(this.searchText);

      },
      error => {
        console.log(error);
      }
    );
  }

  filterPosts(value: string): void {

    this.searchText = value;

    const search = value.toLowerCase().trim();

    if (!search) {
      this.filteredPosts = this.posts;
      return;
    }

    this.filteredPosts = this.posts.filter(post => {

      const content = post.postContent?.toLowerCase() || '';
      const author = post.postAuthor?.toLowerCase() || '';

      return content.includes(search) || author.includes(search);

    });
  }
  doComment(content, postId){
  this.commentService.doComment(content, postId).subscribe(response => {
      this.getAllPosts();
    }
  );
  }
  doReaction(postId: number, reaction: string) {
    this.reactionService.getReaction(postId).subscribe(existingReaction => {

      if (!existingReaction) {
        this.reactionService.doReaction(postId, reaction).subscribe(() => {
          this.getAllPosts();
        });
        return;
      }

      if (existingReaction.reaction === reaction) {
        this.reactionService.deleteReaction(postId).subscribe(() => {
          this.getAllPosts();
        });
      } else {
        this.reactionService.deleteReaction(postId).subscribe(() => {
          this.reactionService.doReaction(postId, reaction).subscribe(() => {
            this.getAllPosts();
          });
        });
      }

    });
  }
  getProfile(){
    this.profileService.getProfile().subscribe(
      response =>{ this.profile = response,
      this.currentUserId= response?.user.id}
    );
  }
  timeAgo(date: string): string {
    const safeDate = date.substring(0, 19); // 2026-02-01T18:54:30
    const past = new Date(safeDate).getTime();
    const now = Date.now();

    const diff = Math.floor((now - past) / 1000);

    if (diff < 60) { return 'just now'; }
    const min = Math.floor(diff / 60);
    if (min < 60) { return `${min} min ago`; }
    const h = Math.floor(min / 60);
    if (h < 24) { return `${h} h ago`; }
    const d = Math.floor(h / 24);
    return `${d} d ago`;
  }
  getAllFriends(): void {
    this.notFriends.getAllFriendResponseForLoginUser().subscribe(
      response => {
        this.friends = response;
      }
    )
  }
  toggleComments(post: any): void {
    post.showComments = !post.showComments;
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
  editingCommentId: number | null = null;
  editedContent = '';

  editComment(comment: any) {
    this.editingCommentId = comment.comment_id;
    this.editedContent = comment.comment_content;
  }

  cancelEdit() {
    this.editingCommentId = null;
    this.editedContent = '';
  }

  updateComment(comment: Comments) {
    debugger
    this.commentService.updateComment(comment).subscribe(
      response => {
        this.getAllPosts();
        this.cancelEdit()
      }
    )

  }
  deleteComment(commentId: number) {
    this.commentService.deleteComment(commentId).subscribe(
      response => {
        this.getAllPosts();
      }
    )
  }

}
