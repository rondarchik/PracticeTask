package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.request.AddCreditRequestDto;
import org.system.creditmanagementsystem.dto.request.GetCreditRequestDto;
import org.system.creditmanagementsystem.dto.request.UpdateCreditRequestDto;
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

    public List<GetCreditRequestDto> getAllCreditRequests() {
        List<CreditRequest> requests = creditRequestRepository.findAll();
        return requests.stream().map(request -> {
            GetCreditRequestDto requestDto = creditRequestMapper.toDto(request);
            String user = request.getUser().getName() +
                    " " +
                    request.getUser().getSurname();
            requestDto.setManager(user);
            requestDto.setCredit(request.getCredit().getId());
            requestDto.setStatus(request.getRequestStatus().getStatus());
            return requestDto;
        }).toList();
    }

    public GetCreditRequestDto getCreditRequestById(UUID id) {
        CreditRequest creditRequest = creditRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return creditRequestMapper.toDto(creditRequest);
    }

    public GetCreditRequestDto addCreditRequest(AddCreditRequestDto creditRequestDto) {
        CreditRequest creditRequest = creditRequestMapper.fromDto(creditRequestDto);
        creditRequestRepository.save(creditRequest);
        return creditRequestMapper.toDto(creditRequest);
    }

    public GetCreditRequestDto updateCreditRequest(UpdateCreditRequestDto creditRequestDto, UUID id) {
        CreditRequest existingCreditRequest = creditRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (creditRequestDto.getDateOfRequest() != null) {
            existingCreditRequest.setDateOfRequest(creditRequestDto.getDateOfRequest());
        }
        if (creditRequestDto.getStatus() != null) {
            existingCreditRequest.setRequestStatus(creditRequestDto.getStatus());
        }
        if (creditRequestDto.getRejectionMessage() != null) {
            existingCreditRequest.setRejectionMessage(creditRequestDto.getRejectionMessage());
        }
        creditRequestRepository.save(existingCreditRequest);
        return creditRequestMapper.toDto(existingCreditRequest);
    }

    public void removeCreditRequestById(UUID id) {
        Optional<CreditRequest> existingCreditRequest = creditRequestRepository.findById(id);

        if (existingCreditRequest.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditRequestRepository.deleteById(id);
    }
}
