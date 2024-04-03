package org.system.creditmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.entity.CreditType;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.repository.CreditTypeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CreditTypeService {
    private final CreditTypeRepository creditTypeRepository;
    private static final String NOT_FOUND_MESSAGE = "Such type not found!";

    public CreditTypeService(CreditTypeRepository creditTypeRepository) {
        this.creditTypeRepository = creditTypeRepository;
    }

    public List<CreditType> getCreditTypes() {
        return creditTypeRepository.findAll();
    }

    public CreditType getCreditTypeById(UUID id) {
        return creditTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    public void addCreditType(CreditType creditType) {
        creditTypeRepository.save(creditType);
    }

    public void updateCreditType(CreditType creditType) {
        var existingCreditType = creditTypeRepository.findById(creditType.getId());

        if (existingCreditType.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditTypeRepository.save(creditType);
    }

    public void removeCreditTypeById(UUID id) {
        var existingCreditType = creditTypeRepository.findById(id);

        if (existingCreditType.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditTypeRepository.deleteById(id);
    }
}
