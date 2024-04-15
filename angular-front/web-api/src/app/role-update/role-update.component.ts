import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgFor} from "@angular/common";
import {Role} from "../entites/role";
import {UserService} from "../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RoleService} from "../services/role.service";

@Component({
  selector: 'app-role-update',
  standalone: true,
  imports: [FormsModule, NgFor],
  templateUrl: './role-update.component.html'
})
export class RoleUpdateComponent implements OnInit {
  role: Role = new Role();
  id: string;

  constructor(private router: Router,
              private activateRoute: ActivatedRoute,
              private roleService: RoleService) {
    this.id = activateRoute.snapshot.params["id"];
  }

  async ngOnInit(){
    this.role = await this.roleService.getRoleById(this.id);
  }

  async updateRole() {
    await this.roleService.updateRole(this.role, this.id);
    this.router.navigate(["/api/roles"]);
  }

}
