import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgFor} from "@angular/common";
import {Credit} from "../entites/credit";
import {CreditService} from "../services/credit.service";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'app-credit.list',
  standalone: true,
  imports: [
    RouterLink,
    NgFor,
    FaIconComponent
  ],
  templateUrl: './credit.list.component.html',
  styleUrl: './credit.list.component.css'
})

export class CreditListComponent {
  credits: Credit[] = [];

  constructor(private creditService: CreditService) {
  }

  async ngOnInit() {
    this.credits = await this.creditService.getCredits();
  }

  formatDate(dateString: string): string {
    const options = {day: '2-digit' as const, month: '2-digit' as const, year: 'numeric' as const};
    return new Date(dateString).toLocaleDateString(undefined, options);
  }
}
