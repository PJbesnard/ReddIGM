import { Deserializable } from "./deserializable.model";

export class SubjectModel implements Deserializable {
  id?: number;
  name: string = "";
  description: string = "";

  deserialize(input: any): this {
		Object.assign(this, input);
		return this;
	}
}
