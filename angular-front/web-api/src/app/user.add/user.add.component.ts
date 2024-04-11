import { Component } from '@angular/core';
import {NgFor, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {User} from "../entites/user";
import {Role} from "../entites/role";
import {UserService} from "../services/user.service";
import {RoleService} from "../services/role.service";
import {Router} from "@angular/router";
import { UUID } from "angular2-uuid";

@Component({
  selector: 'app-user.add',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './user.add.component.html',
  styleUrl: './user.add.component.css'
})

export class UserAddComponent {
  user: User = new User();
  roleList: Role[] = [];

  constructor(private userService: UserService,
              private roleService: RoleService,
              private router: Router) {}

  async ngOnInit(){
    this.roleList = await this.roleService.getRoles();
  }

  async addUser() {
    this.user.id = UUID.UUID();
    await this.userService.addUser(this.user);
    this.router.navigate(["/api/users"]);
  }
}
