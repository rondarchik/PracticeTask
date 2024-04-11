import { Component } from '@angular/core';
import {Role} from "../entites/role";
import {RoleService} from "../services/role.service";
import {RouterLink} from "@angular/router";
import { faPlus, faTimes, faEdit } from '@fortawesome/free-solid-svg-icons';
import { library } from '@fortawesome/fontawesome-svg-core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-role-list',
  standalone: true,
  imports: [RouterLink, FaIconComponent, NgForOf],
  templateUrl: './role.list.component.html',
  styleUrl: './role.list.component.css'
})

export class RoleListComponent {
  roles: Role[] = [];

  constructor(private roleService: RoleService) {
    library.add(faPlus, faTimes, faEdit);
  }

  async ngOnInit(){
    this.roles = await this.roleService.getRoles();
  }

  async removeRole(id: string) {
    await this.roleService.removeRoleById(id);
    this.ngOnInit();
  }

  async removeUserInRole(roleId: string, userId: string) {
    await this.roleService.removeUserFromRole(roleId, userId);
    this.ngOnInit();
  }

  protected readonly faPlus = faPlus;
  protected readonly faTimes = faTimes;
  protected readonly faEdit = faEdit;
}
