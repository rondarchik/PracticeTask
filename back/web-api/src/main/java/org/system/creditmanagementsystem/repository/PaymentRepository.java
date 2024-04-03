package org.system.creditmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.system.creditmanagementsystem.entity.Payment;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

}