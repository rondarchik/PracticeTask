import { Injectable } from '@angular/core';
import axios from "axios";

@Injectable({
  providedIn: 'root'
})

export class RequestStatusService {
  readonly STATUS_URL: string;

  constructor() {
    this.STATUS_URL =  "http://localhost:8080/api/request_statuses";
  }

  async getRequestStatuses() {
    const response = await axios.get(this.STATUS_URL);
    return response.data;
  }
}
