import { Routes } from '@angular/router';
import { RoleListComponent } from "./role.list/role.list.component";
import { UserListComponent } from "./user.list/user.list.component";
import {UserAddComponent} from "./user.add/user.add.component";
import {UserUpdateComponent} from "./user.update/user.update.component";

export const routes: Routes = [
  { path: "api/roles", component: RoleListComponent },
  { path: "api/users", component: UserListComponent },
  { path: "api/users/add", component: UserAddComponent },
  { path: "api/users/update/:id", component: UserUpdateComponent }
];
