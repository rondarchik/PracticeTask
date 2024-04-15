import {Component, OnInit} from '@angular/core'
import {NgFor, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {User} from "../entites/user";
import {Role} from "../entites/role";
import {UserService} from "../services/user.service";
import {RoleService} from "../services/role.service";
import {Router} from "@angular/router";
import { UUID } from "angular2-uuid";

@Component({
  selector: 'app-role-add',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './role-add.component.html'
})
export class RoleAddComponent implements OnInit {
  role: Role = new Role();
  userList: User[] = [];

  constructor(private userService: UserService,
              private roleService: RoleService,
              private router: Router) {}

  async ngOnInit(){
    this.userList = await this.userService.getUsers();
  }

  async addRole() {
    this.role.id = UUID.UUID();
    await this.roleService.addRole(this.role);
    this.router.navigate(["/api/roles"]);
  }
}
