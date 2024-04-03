package org.system.creditmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.entity.RequestStatus;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.repository.RequestStatusRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RequestStatusService {
    private final RequestStatusRepository requestStatusRepository;
    private static final String NOT_FOUND_MESSAGE = "Such status not found!";

    public RequestStatusService(RequestStatusRepository requestStatusRepository) {
        this.requestStatusRepository = requestStatusRepository;
    }

    public List<RequestStatus> getAllRequestStatuses() {
        return requestStatusRepository.findAll();
    }

    public RequestStatus getRequestStatusById(UUID id) {
        return requestStatusRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    public RequestStatus addRequestStatus(RequestStatus requestStatus) {
        return requestStatusRepository.save(requestStatus);
    }

    public RequestStatus updateRequestStatus(RequestStatus requestStatus) {
        var existingRequestStatus = requestStatusRepository.findById(requestStatus.getId());

        if (existingRequestStatus.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        return requestStatusRepository.save(requestStatus);
    }

    public void removeRequestStatusById(UUID id) {
        var existingRequestStatus = requestStatusRepository.findById(id);

        if (existingRequestStatus.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        requestStatusRepository.deleteById(id);
    }
}
