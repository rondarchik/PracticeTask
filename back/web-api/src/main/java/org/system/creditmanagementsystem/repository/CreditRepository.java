package org.system.creditmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.system.creditmanagementsystem.entity.Credit;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {
    List<Credit> findByCreditTypeId(UUID typeId);
}
