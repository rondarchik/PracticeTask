import { Injectable } from '@angular/core';
import axios from "axios";

@Injectable({
  providedIn: 'root'
})

export class CreditTypeService {
  readonly TYPE_URL: string;

  constructor() {
    this.TYPE_URL =  "http://localhost:8080/api/credit_types";
  }

  async getCreditTypes() {
    const response = await axios.get(this.TYPE_URL);
    return response.data;
  }
}
