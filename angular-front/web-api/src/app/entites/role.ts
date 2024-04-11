import {User} from './user';

export class Role {
  id!: string;
  roleName!: string;
  users: User[] = []
}
