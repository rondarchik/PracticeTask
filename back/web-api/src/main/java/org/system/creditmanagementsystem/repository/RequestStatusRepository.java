package org.system.creditmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.system.creditmanagementsystem.entity.RequestStatus;

import java.util.UUID;

@Repository
public interface RequestStatusRepository extends JpaRepository<RequestStatus, UUID> {

}