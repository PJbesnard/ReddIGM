import { Datable } from "./datable.model";
import { SubjectModel } from "./subject-response";
import { User } from "./user.model";
import { VoteType } from "./vote-type.enum";

export class PostModel {
    id!: number;
    postName!: string;
	url!: string;
	description!: string;
	voteCount!: number;
	//userId!:number;
	sub!: SubjectModel;
	duration! : string;
	//subjectId!:number;
	user!: User;
	myVote!: VoteType;
	nbComments!: number;


	getDate(): Date {
        return new Date(this.duration);
    }
  }
