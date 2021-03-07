import { Datable } from "./datable.model";

export class Comment implements Datable {
    postId: number = 0;
    commentId: number = 0;
    text: string = "";
    userName: string = "unknown";
    creationDate: string = "15/02/2021";
    nbVote: number = 0;
    picture: string = "";

    getDate(): Date {
        return new Date(this.creationDate);
    }
}