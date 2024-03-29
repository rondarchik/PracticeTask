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
@Table(name = "Clients")
public class Client {
    @Id
    @Column(name = "client_id")
    protected UUID clientID;

    @Column(name = "birth_date")
    protected Date birthDate;
}
