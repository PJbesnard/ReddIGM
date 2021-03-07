import { Datable } from "./datable.model";
import { VoteType } from "./vote-type.enum";

export class PostModel {
    id!: number;
    postName!: string;
	url!: string;
	description!: string;
	voteCount!: number;
	userId!:number;
	createdDate! : string;
	subjectId!:number;
	myVote!: VoteType;

	getDate(): Date {
        return new Date(this.createdDate);
    }
  }
