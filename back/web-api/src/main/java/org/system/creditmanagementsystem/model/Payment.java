package org.system.creditmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;

    @Column(name="id_client")
    private UUID clientID;

    @Column(name="id_credit")
    private UUID creditID;

    @Column(name="amount")
    private Double amount;

    @Column(name="payment_date")
    private Date paymentDate;
}
