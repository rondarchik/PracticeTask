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
@Table(name="credit_request")
public class CreditRequest {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;

    @Column(name="id_manager")
    private UUID managerID;

    @Column(name="id_credit")
    private UUID creditID;

    @Column(name="date_of_request")
    private Date dateOfRequest;

    @Column(name="id_status")
    private UUID statusID;

    @Column(name="rejection_message")
    private String rejectionMessage;
}
