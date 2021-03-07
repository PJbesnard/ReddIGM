import { Datable } from "./datable.model";

export class PostModel {
    id!: number;
    postName!: string;
	url!: string;
	description!: string;
	voteCount!: number;
	userId!:number;
	createdDate! : string;
	subjectId!:number;
	upVote!: boolean;
    downVote!: boolean;

	getDate(): Date {
        return new Date(this.createdDate);
    }
  }