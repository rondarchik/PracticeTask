package com.creditsystem.creditsystem.entities;

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
@Table(name = "Credit_Request")
public class CreditRequest {
    @Id
    @Column(name = "id")
    protected UUID id;

    @Column(name = "manager_id")
    protected UUID managerID;

    @Column(name = "client_id")
    protected UUID clientID;

    @Column(name = "credit_type_id")
    protected UUID creditTypeID;

    @Column(name = "date_of_request")
    protected Date dateOfRequest;

    @Column(name = "status")
    protected Boolean status;

}
