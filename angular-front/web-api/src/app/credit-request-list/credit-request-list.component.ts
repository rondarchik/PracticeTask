import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgFor} from "@angular/common";
import {CreditRequestService} from "../services/credit-request.service";
import {CreditRequest} from "../entites/credit.request";

@Component({
  selector: 'app-credit-request-list',
  standalone: true,
  imports: [
    RouterLink,
    NgFor
  ],
  templateUrl: './credit-request-list.component.html'
})

export class CreditRequestListComponent implements OnInit {
  creditsRequests: CreditRequest[] = [];

  constructor(private creditRequestService: CreditRequestService) {
  }

  async ngOnInit() {
    this.creditsRequests = await this.creditRequestService.getCreditRequests();
  }

  formatDate(dateString: string): string {
    const options = {day: '2-digit' as const, month: '2-digit' as const, year: 'numeric' as const};
    return new Date(dateString).toLocaleDateString(undefined, options);
  }
}
