package org.system.creditmanagementsystem.entity;

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
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_credit")
    private Credit credit;
}
