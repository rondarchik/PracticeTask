import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgFor} from "@angular/common";
import {Payment} from "../entites/payment";
import {PaymentService} from "../services/payment.service";

@Component({
  selector: 'app-payment.list',
  standalone: true,
  imports: [
    RouterLink,
    NgFor
  ],
  templateUrl: './payment.list.component.html',
  styleUrl: './payment.list.component.css'
})

export class PaymentListComponent {
  payments: Payment[] = [];

  constructor(private paymentService: PaymentService) {
  }

  async ngOnInit() {
    this.payments = await this.paymentService.getPayments();
  }

  formatDate(dateString: string): string {
    const options = {day: '2-digit' as const, month: '2-digit' as const, year: 'numeric' as const};
    return new Date(dateString).toLocaleDateString(undefined, options);
  }
}
