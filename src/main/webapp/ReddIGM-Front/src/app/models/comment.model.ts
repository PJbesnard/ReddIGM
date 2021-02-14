import { User } from "./user.model";

export class Comment {
    user!: User;
    content!: string;
    date!: Date;
}