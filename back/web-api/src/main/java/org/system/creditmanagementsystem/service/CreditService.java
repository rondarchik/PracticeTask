package org.system.creditmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.repository.CreditRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private static final String NOT_FOUND_MESSAGE = "Such credit not found!";

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public List<Credit> getCredits() {
        return creditRepository.findAll();
    }

    public Credit getCreditById(UUID id) {
        return creditRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    public void addCredit(Credit credit) {
        creditRepository.save(credit);
    }

    public void updateCredit(Credit credit) {
        var existingCredit = creditRepository.findById(credit.getId());

        if (existingCredit.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditRepository.save(credit);
    }

    public void removeCreditById(UUID id) {
        var existingCredit = creditRepository.findById(id);

        if (existingCredit.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditRepository.deleteById(id);
    }

}
