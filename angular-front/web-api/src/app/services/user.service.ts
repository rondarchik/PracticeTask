import { Injectable } from '@angular/core';
import axios from "axios";
import {User} from "../entites/user";

@Injectable({
  providedIn: 'root'
})

export class UserService {
  readonly USER_URL: string;

  constructor() {
    this.USER_URL =  "http://localhost:8080/api/users";
  }

  async getUsers() {
    const response = await axios.get("http://localhost:8080/api/users");
    return response.data;
  }

  async addUser(user: User) {
    const response = await axios.post("http://localhost:8080/api/users/add", user);
    return response.data;
  }

  async removeUserById(id: string) {
    const response = await axios.delete(`http://localhost:8080/api/users/delete/${id}`);
    return response.data;
  }

  async updateUser(user: User, id: string) {
    const response = await axios.put(`http://localhost:8080/api/users/update/${id}`, user);
    return response.data;
  }

  async getUserById(id: string) {
    const response = await axios.get(`http://localhost:8080/api/users/${id}`);
    return response.data;
  }
}
