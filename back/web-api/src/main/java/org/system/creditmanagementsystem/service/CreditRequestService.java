package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.CreditRequestDto;
import org.system.creditmanagementsystem.entity.CreditRequest;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.CreditRequestMapper;
import org.system.creditmanagementsystem.repository.CreditRequestRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditRequestService {
    private final CreditRequestRepository creditRequestRepository;
    private final CreditRequestMapper creditRequestMapper;
    private static final String NOT_FOUND_MESSAGE = "Such credit request on credit not found!";

    @Autowired
    public CreditRequestService(CreditRequestRepository creditRequestRepository, CreditRequestMapper creditRequestMapper) {
        this.creditRequestRepository = creditRequestRepository;
        this.creditRequestMapper = creditRequestMapper;
    }

    public List<CreditRequestDto> getAllCreditRequests() {
        return creditRequestRepository.findAll().stream().map(creditRequestMapper::toDto).toList();
    }

    public CreditRequestDto getCreditRequestById(UUID id) {
        CreditRequest creditRequest = creditRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return creditRequestMapper.toDto(creditRequest);
    }

    public List<CreditRequestDto> getStatusRequests(UUID statusId) {
        return creditRequestRepository.findByRequestStatusId(statusId).stream().map(creditRequestMapper::toDto).toList();
    }

    public CreditRequestDto addCreditRequest(CreditRequestDto creditRequestDto) {
        CreditRequest creditRequest = creditRequestMapper.fromDto(creditRequestDto);
        creditRequestRepository.save(creditRequest);
        return creditRequestMapper.toDto(creditRequest);
    }

    public CreditRequestDto updateCreditRequest(CreditRequestDto creditRequestDto, UUID id) {
        CreditRequest creditRequest = creditRequestMapper.fromDto(creditRequestDto);
        Optional<CreditRequest> existingCreditRequest = creditRequestRepository.findById(id);

        if (existingCreditRequest.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditRequest.setDateOfRequest(creditRequestDto.getDateOfRequest());
        creditRequest.setRejectionMessage(creditRequestDto.getRejectionMessage());
        CreditRequest updatedCreditRequest = creditRequestRepository.save(creditRequest);
        return creditRequestMapper.toDto(updatedCreditRequest);
    }

    public void removeCreditRequestById(UUID id) {
        Optional<CreditRequest> existingCreditRequest = creditRequestRepository.findById(id);

        if (existingCreditRequest.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditRequestRepository.deleteById(id);
    }
}
