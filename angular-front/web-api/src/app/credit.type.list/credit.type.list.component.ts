import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgFor} from "@angular/common";
import {CreditTypeService} from "../services/credit.type.service";
import {CreditType} from "../entites/credit.type";

@Component({
  selector: 'app-credit.type.list',
  standalone: true,
  imports: [
    RouterLink,
    NgFor
  ],
  templateUrl: './credit.type.list.component.html',
  styleUrl: './credit.type.list.component.css'
})

export class CreditTypeListComponent {
  creditTypes: CreditType[] = [];

  constructor(private creditTypeService: CreditTypeService) {
  }

  async ngOnInit() {
    this.creditTypes = await this.creditTypeService.getCreditTypes();
  }
}
