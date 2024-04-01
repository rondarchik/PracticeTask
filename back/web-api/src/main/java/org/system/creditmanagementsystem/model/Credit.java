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
@Table(name="credit")
public class Credit {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;

    @Column(name="id_client")
    private UUID clientID;

    @Column(name="id_credit_type")
    private UUID creditTypeID;

    @Column(name="paid_amount")
    private Double paidAmount;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="status")
    private Boolean status;
}
