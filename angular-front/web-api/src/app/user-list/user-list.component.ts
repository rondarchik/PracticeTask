import {Component, OnInit} from '@angular/core';
import {User} from "../entites/user";
import {UserService} from "../services/user.service";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faEdit, faPlus, faTimes} from "@fortawesome/free-solid-svg-icons";
import {RouterLink} from "@angular/router";
import {library} from "@fortawesome/fontawesome-svg-core";
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [
    FaIconComponent,
    RouterLink,
    NgFor
  ],
  templateUrl: './user-list.component.html'
})

export class UserListComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) {
    library.add(faPlus, faTimes, faEdit);
  }

  async ngOnInit() {
    this.users = await this.userService.getUsers();
  }

  async removeUser(id: string) {
    await this.userService.removeUserById(id);
    this.users = this.users.filter(user => user.id !== id);
  }

  formatDate(dateString: string): string {
    const options = {day: '2-digit' as const, month: '2-digit' as const, year: 'numeric' as const};
    return new Date(dateString).toLocaleDateString(undefined, options);
  }

  protected readonly faPlus = faPlus;
  protected readonly faTimes = faTimes;
  protected readonly faEdit = faEdit;
}
