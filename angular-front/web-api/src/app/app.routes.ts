import { Routes } from '@angular/router';
import { RoleListComponent } from "./role.list/role.list.component";
import { UserListComponent } from "./user.list/user.list.component";
import {UserAddComponent} from "./user.add/user.add.component";
import {UserUpdateComponent} from "./user.update/user.update.component";
import {RoleAddComponent} from "./role.add/role.add.component";
import {CreditListComponent} from "./credit.list/credit.list.component";
import {CreditTypeListComponent} from "./credit.type.list/credit.type.list.component";
import {CreditRequestListComponent} from "./credit.request.list/credit.request.list.component";
import {PaymentListComponent} from "./payment.list/payment.list.component";
import {RequestStatusListComponent} from "./request.status.list/request.status.list.component";

export const routes: Routes = [
  { path: "api/roles", component: RoleListComponent },
  { path: "api/users", component: UserListComponent },
  { path: "api/users/add", component: UserAddComponent },
  { path: "api/users/update/:id", component: UserUpdateComponent },
  { path: "api/roles/add", component: RoleAddComponent },
  { path: "api/roles/update/:id", component: UserUpdateComponent },
  { path: "api/credits", component: CreditListComponent },
  { path: "api/credit_types", component: CreditTypeListComponent },
  { path: "api/credit_requests", component: CreditRequestListComponent },
  { path: "api/payments", component: PaymentListComponent },
  { path: "api/request_statuses", component: RequestStatusListComponent }
];
