package org.system.creditmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.system.creditmanagementsystem.model.CreditType;

import java.util.UUID;

@Repository
public interface CreditTypeRepository extends JpaRepository<CreditType, UUID> {

}