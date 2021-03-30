import { Authorities } from "./authorities.model";
import { Deserializable } from "./deserializable.model";

export class User implements Deserializable {
  public static readonly defaultPicture: string = 'assets/profile_picture.png'

  id!: number;
  username!: string;
  email!: string;
  description!: string;
  private picture!: string;
  newsletterSubscriber!: boolean;
  authorities!: Authorities[];

  constructor() {
    this.authorities = new Array<Authorities>();
  }

  deserialize(input: any): this {
    Object.assign(this, input);
    return this;
  }

  public getPicture(): string {
    if (!this.picture) {
      this.picture = User.defaultPicture;
    }

    return this.picture;
  }

  public setPicture(url: string): void {
    this.picture = url;
  }

  public isAdmin(): boolean {
    return this.authorities.find(authority => authority.authority === "ADMIN") != undefined;
  }
}
