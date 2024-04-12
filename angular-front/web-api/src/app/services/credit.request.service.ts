import { Injectable } from '@angular/core';
import axios from "axios";

@Injectable({
  providedIn: 'root'
})

export class CreditRequestService {
  readonly REQUEST_URL: string;

  constructor() {
    this.REQUEST_URL =  "http://localhost:8080/api/credit_requests";
  }

  async getCreditRequests() {
    const response = await axios.get(this.REQUEST_URL);
    return response.data;
  }
}
