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
@Table(name = "credit_request")
public class CreditRequest {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "date_of_request")
    private Date dateOfRequest;

    @Column(name = "rejection_message")
    private String rejectionMessage;

    @ManyToOne
    @JoinColumn(name = "id_manager")
    private User user;

    @OneToOne
    @JoinColumn(name = "id_credit")
    private Credit credit;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private RequestStatus requestStatus;
}
