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
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    private Boolean status;

    @OneToOne(mappedBy = "credit", cascade = CascadeType.ALL)
    private CreditRequest creditRequest;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_credit_type")
    private CreditType creditType;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private Set<Payment> payments = new LinkedHashSet<>();
}
