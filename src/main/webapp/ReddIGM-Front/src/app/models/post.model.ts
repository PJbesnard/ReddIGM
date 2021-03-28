import { Datable } from "./datable.model";
import { Deserializable } from "./deserializable.model";
import { SubjectModel } from "./subject-response";
import { User } from "./user.model";
import { VoteType } from "./vote-type.enum";

export class PostModel implements Datable, Deserializable {
    id!: number;
    postName!: string;
	url!: string;
	description!: string;
	voteCount!: number;
	sub!: SubjectModel;
	private duration! : string;
	user!: User;
	myVote!: VoteType;
	nbComments!: number;

	constructor() {
		this.sub = new SubjectModel();
		this.user = new User();
	}

	getDate(): Date {
        return new Date(this.duration);
    }

	deserialize(input: any): this {
		Object.assign(this, input);

		if (input.sub != undefined) {
			this.sub = new SubjectModel().deserialize(input.sub);
		}
		
		if (input.user != undefined) {
			this.user = new User().deserialize(input.user);
		}

		return this;
	}
  }
