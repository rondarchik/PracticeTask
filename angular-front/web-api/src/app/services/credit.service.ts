import { Injectable } from '@angular/core';
import axios from "axios";

@Injectable({
  providedIn: 'root'
})

export class CreditService {
  readonly CREDIT_URL: string;

  constructor() {
    this.CREDIT_URL =  "http://localhost:8080/api/credits";
  }

  async getCredits() {
    const response = await axios.get(this.CREDIT_URL);
    return response.data;
  }
}
