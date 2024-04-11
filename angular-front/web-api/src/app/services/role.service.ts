import { Injectable } from '@angular/core';
import axios from "axios";
import {Role} from "../entites/role";

@Injectable({
  providedIn: 'root'
})

export class RoleService {

  constructor() {}

  async getRoles() {
    const response = await axios.get("http://localhost:8080/api/roles");
    return response.data;
  }

  async getRoleById(id: string) {
    const response = await axios.get(`http://localhost:8080/api/roles/${id}`);
    return response.data;
  }

  async addRole(role: Role) {
    const response = await axios.post("http://localhost:8080/api/roles/add", role);
    return response.data;
  }

  async removeRoleById(id: string) {
    const response = await axios.delete(`http://localhost:8080/api/roles/delete/${id}`);
    return response.data;
  }

  async updateRole(role: Role, id: string) {
    const response = await axios.put(`http://localhost:8080/api/roles/update/${id}`, role);
    return response.data;
  }

  async removeUserFromRole(roleId: string, userId: string) {
    const response = await axios.delete(`http://localhost:8080/api/roles/${roleId}/users/${userId}`);
    return response.data;
  }
}
