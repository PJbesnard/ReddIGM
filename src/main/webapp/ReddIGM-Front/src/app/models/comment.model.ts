import { Datable } from "./datable.model";
import { User } from "./user.model";
import { VoteType } from "./vote-type.enum";

export class Comment implements Datable {
    postId: number = 0;
    commentId: number = 0;
    text: string = "";
    userName: string = "unknown";
    creationDate: string = "15/02/2021";
    nbVote: number = 0;
    picture: string = "";
    myVote!: VoteType;
    nbComments: number = 0;
    user!: User;

    getDate(): Date {
        return new Date(this.creationDate);
    }
}