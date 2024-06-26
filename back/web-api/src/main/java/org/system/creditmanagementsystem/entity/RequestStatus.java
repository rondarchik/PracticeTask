package org.system.creditmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request_status")
public class RequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "requestStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CreditRequest> creditRequests = new LinkedHashSet<>();
}
