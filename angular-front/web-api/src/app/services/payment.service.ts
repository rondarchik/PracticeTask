import { Injectable } from '@angular/core';
import axios from "axios";

@Injectable({
  providedIn: 'root'
})

export class PaymentService {
  readonly PAYMENT_URL: string;

  constructor() {
    this.PAYMENT_URL =  "http://localhost:8080/api/payments";
  }

  async getPayments() {
    const response = await axios.get(this.PAYMENT_URL);
    return response.data;
  }
}
