
import {Comments} from '../comment/comments';
import {Media} from '../media/media';

export class Post {
    postId:number;
    postContent:string;
  postAuthor:string;
   responseComments:Comments[];
   numberOfLikes:number;
   numberOfDislikes:number;
   mediaUrl:string[];
  createdAt:string;
  imageUrl:string;
}
