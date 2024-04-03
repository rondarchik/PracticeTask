package org.system.creditmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "password_hash")
    private String passwordHash;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Credit> credits = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CreditRequest> creditRequests = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Payment> payments = new LinkedHashSet<>();
}
