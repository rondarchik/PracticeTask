package org.system.creditmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.entity.CreditRequest;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.repository.CreditRequestRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CreditRequestService {
    private final CreditRequestRepository creditRequestRepository;
    private static final String NOT_FOUND_MESSAGE = "Such request on credit not found!";

    public CreditRequestService(CreditRequestRepository creditRequestRepository) {
        this.creditRequestRepository = creditRequestRepository;
    }

    public List<CreditRequest> getAllCreditRequests() {
        return creditRequestRepository.findAll();
    }

    public CreditRequest getCreditRequestById(UUID id) {
        return creditRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    public List<CreditRequest> getStatusRequests(UUID statusId) {
        return creditRequestRepository.findByRequestStatusId(statusId);
    }

    public CreditRequest addCreditRequest(CreditRequest creditRequest) {
        return creditRequestRepository.save(creditRequest);
    }

    public CreditRequest updateCreditRequest(CreditRequest creditRequest) {
        var existingCreditRequest = creditRequestRepository.findById(creditRequest.getId());

        if (existingCreditRequest.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        return creditRequestRepository.save(creditRequest);
    }

    public void removeCreditRequestById(UUID id) {
        var existingCreditRequest = creditRequestRepository.findById(id);

        if (existingCreditRequest.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditRequestRepository.deleteById(id);
    }
}
