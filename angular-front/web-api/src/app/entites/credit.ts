import {CreditType} from "./credit.type";

export class Credit {
  id!: string;
  client!: string;
  creditType!: CreditType;
  paidAmount!: number;
  startDate!: string;
  endDate!: string;
  status!: boolean;
}
