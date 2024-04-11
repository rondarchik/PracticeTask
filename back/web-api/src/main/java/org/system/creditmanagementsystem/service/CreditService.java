package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.credit.AddCreditDto;
import org.system.creditmanagementsystem.dto.credit.GetCreditDto;
import org.system.creditmanagementsystem.dto.credit.UpdateCreditDto;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.CreditMapper;
import org.system.creditmanagementsystem.repository.CreditRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;
    private static final String NOT_FOUND_MESSAGE = "Such credit not found!";

    @Autowired
    public CreditService(CreditRepository creditRepository, CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.creditMapper = creditMapper;
    }

    public List<GetCreditDto> getAllCredits() {
        List<Credit> credits =  creditRepository.findAll();
        return credits.stream().map(credit -> {
            GetCreditDto creditDto = creditMapper.toDto(credit);
            String user = credit.getUser().getName() +
                    " " +
                    credit.getUser().getSurname();
            creditDto.setClient(user);
            return creditDto;
        }).toList();
    }

    public GetCreditDto getCreditById(UUID id) {
        Credit credit = creditRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return creditMapper.toDto(credit);
    }

    public GetCreditDto addCredit(AddCreditDto creditDto) {
        Credit credit = creditMapper.fromDto(creditDto);
        creditRepository.save(credit);
        return creditMapper.toDto(credit);
    }

    public GetCreditDto updateCredit(UpdateCreditDto creditDto, UUID id) {
        Credit existingCredit = creditRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (creditDto.getPaidAmount() != null) {
            existingCredit.setPaidAmount(creditDto.getPaidAmount());
        }
        if (creditDto.getStartDate() != null) {
            existingCredit.setStartDate(creditDto.getStartDate());
        }
        if (creditDto.getEndDate() != null) {
            existingCredit.setEndDate(creditDto.getEndDate());
        }
        if (creditDto.getStatus() != null) {
            existingCredit.setStatus(creditDto.getStatus());
        }
        if (creditDto.getCreditType() != null) {
            existingCredit.setCreditType(creditDto.getCreditType());
        }

        creditRepository.save(existingCredit);
        return creditMapper.toDto(existingCredit);
    }

    public void removeCreditById(UUID id) {
        Optional<Credit> existingCredit = creditRepository.findById(id);

        if (existingCredit.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditRepository.deleteById(id);
    }

}
