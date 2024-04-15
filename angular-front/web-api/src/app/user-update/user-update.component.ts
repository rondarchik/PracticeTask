import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgFor} from "@angular/common";
import {Role} from "../entites/role";
import {User} from "../entites/user";
import {UserService} from "../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RoleService} from "../services/role.service";

@Component({
  selector: 'app-user-update',
  standalone: true,
  imports: [FormsModule, NgFor],
  templateUrl: './user-update.component.html'
})

export class UserUpdateComponent implements OnInit {
  user: User = new User();
  roleList: Role[] = [];
  id: string;

  constructor(private userService: UserService,
              private router: Router,
              private activateRoute: ActivatedRoute,
              private roleService: RoleService) {
    this.id = activateRoute.snapshot.params["id"];
  }

  async ngOnInit(){
    this.user = await this.userService.getUserById(this.id);
    this.roleList = await this.roleService.getRoles();
  }

  async updateUser() {
    await this.userService.updateUser(this.user, this.id);
    this.router.navigate(["/api/users"]);
  }
}
