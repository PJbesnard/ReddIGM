import { Datable } from "./datable.model";
import { Deserializable } from "./deserializable.model";
import { User } from "./user.model";
import { VoteType } from "./vote-type.enum";

export class Comment implements Datable, Deserializable {
    postId: number = 0;
    superCommentId: number = 0;
    commentId: number = 0;
    text: string = "";
    userName: string = "unknown";
    private creationDate: string = "";
    nbVote: number = 0;
    picture: string = "";
    myVote!: VoteType;
    nbComments: number = 0;
    user: User = new User();

    getDate(): Date {
        return new Date(this.creationDate);
    }

	deserialize(input: any): this {
		Object.assign(this, input);
        if (input.user != undefined) {
            this.user = new User().deserialize(input.user);
        }
		return this;
	}
}