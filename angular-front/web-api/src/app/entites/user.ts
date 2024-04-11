import {Role} from './role';

export class User {
  id!: string;
  name!: string;
  surname!: string;
  email!: string;
  birthDate!: string;
  passwordHash!: string;
  roles: Role[] = []
}
