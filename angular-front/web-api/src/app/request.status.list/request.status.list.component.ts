import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgFor} from "@angular/common";
import {RequestStatus} from "../entites/request.status";
import {RequestStatusService} from "../services/request.status.service";

@Component({
  selector: 'app-request.status.list',
  standalone: true,
  imports: [
    RouterLink,
    NgFor
  ],
  templateUrl: './request.status.list.component.html',
  styleUrl: './request.status.list.component.css'
})

export class RequestStatusListComponent {
  requestStatuses: RequestStatus[] = [];

  constructor(private requestStatusService: RequestStatusService) {
  }

  async ngOnInit() {
    this.requestStatuses = await this.requestStatusService.getRequestStatuses();
  }
}
