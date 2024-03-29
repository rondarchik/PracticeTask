package com.creditsystem.creditsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Credit_Types")
public class CreditType {
    @Id
    @Column(name = "id")
    protected UUID id;

    @Column(name = "name")
    protected String name;

    @Column(name = "credit_amount")
    protected Double creditAmount;

    @Column(name = "interest_rate")
    protected Double interestRate;

    @Column(name = "term_in_months")
    protected int termInMonths;

}
