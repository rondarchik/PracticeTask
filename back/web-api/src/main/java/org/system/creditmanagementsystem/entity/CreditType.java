package org.system.creditmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "credit_type")
public class CreditType {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "credit_amount")
    private Double creditAmount;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "term_in_months")
    private int termInMonths;

    @OneToMany(mappedBy = "creditType", cascade = CascadeType.ALL)
    private List<Credit> credits;
}
