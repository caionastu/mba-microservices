import { Image } from './../models/image-model';
import { User } from './../models/user-model';
export class UserView {
  user: User;
  images: Image[] = [];

  constructor(user: User) {
    this.user = user;
  }

  static from(users: User[]): UserView[] {
    return users.map((user) => new UserView(user));
  }
}
