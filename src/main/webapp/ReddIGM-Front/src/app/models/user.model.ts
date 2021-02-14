export class User {
    username!: string;
    email!: string;
    description!: string;
    picture: string = 'assets/profile_picture.png' // Default picture;
    newsletters!: boolean;
  }