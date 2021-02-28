import { User } from "./user.model";

export class Comment {
    postId: number = 0;
    commentId: number = 0;
    text: string = "";
    userName: string = "unknown";
    creationDate: string = "15/02/2021";
    nbVote: number = 0;
    picture: string = "";

}