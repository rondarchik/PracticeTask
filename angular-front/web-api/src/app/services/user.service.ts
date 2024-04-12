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
    const response = await axios.get(this.USER_URL);
    return response.data;
  }

  async addUser(user: User) {
    const response = await axios.post(`${this.USER_URL}/add`, user);
    return response.data;
  }

  async removeUserById(id: string) {
    const response = await axios.delete(`${this.USER_URL}/delete/${id}`);
    return response.data;
  }

  async updateUser(user: User, id: string) {
    const response = await axios.put(`${this.USER_URL}/update/${id}`, user);
    return response.data;
  }

  async getUserById(id: string) {
    const response = await axios.get(`${this.USER_URL}/${id}`);
    return response.data;
  }
}
