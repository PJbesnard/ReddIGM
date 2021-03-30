import { Datable } from "./datable.model";
import { Deserializable } from "./deserializable.model";
import { VoteType } from "./vote-type.enum";

export class VotePost implements Deserializable, Datable {

    postId!: number;
    vote!: VoteType;
    creationDate!: string;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }

	getDate(): Date {
        return new Date(this.creationDate);
    }
}