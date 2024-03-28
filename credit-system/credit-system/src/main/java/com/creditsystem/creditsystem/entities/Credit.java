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
@Table(name = "Credits")
public class Credit {
    @Id
    @Column(name = "client_id")
    protected UUID id;

    @Column(name = "client_id")
    protected UUID clientID;

    @Column(name = "credit_type_id")
    protected UUID creditTypeID;

    @Column(name = "start_date")
    protected Date startDate;

    @Column(name = "end_date")
    protected Date endDate;

    @Column(name = "status")
    protected Boolean status;
}
