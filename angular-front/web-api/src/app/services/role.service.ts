import { Injectable } from '@angular/core';
import axios from "axios";
import {Role} from "../entites/role";

@Injectable({
  providedIn: 'root'
})

export class RoleService {
  readonly ROLE_URL: string;

  constructor() {
    this.ROLE_URL =  "http://localhost:8080/api/roles";
  }

  async getRoles() {
    const response = await axios.get(this.ROLE_URL);
    return response.data;
  }

  async getRoleById(id: string) {
    const response = await axios.get(`${this.ROLE_URL}/${id}`);
    return response.data;
  }

  async addRole(role: Role) {
    const response = await axios.post(`${this.ROLE_URL}/add`, role);
    return response.data;
  }

  async removeRoleById(id: string) {
    const response = await axios.delete(`${this.ROLE_URL}/delete/${id}`);
    return response.data;
  }

  async updateRole(role: Role, id: string) {
    const response = await axios.put(`${this.ROLE_URL}/update/${id}`, role);
    return response.data;
  }

  async removeUserFromRole(roleId: string, userId: string) {
    const response = await axios.delete(`${this.ROLE_URL}/${roleId}/users/${userId}`);
    return response.data;
  }
}
