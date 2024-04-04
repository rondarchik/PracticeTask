package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.CreditDto;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.CreditMapper;
import org.system.creditmanagementsystem.repository.CreditRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<CreditDto> getAllCredits() {
        return creditRepository.findAll().stream().map(creditMapper::toDto).collect(Collectors.toList());
    }

    public CreditDto getCreditById(UUID id) {
        var credit = creditRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return creditMapper.toDto(credit);
    }

    public List<CreditDto> getTypeCredits(UUID typeId) {
        return creditRepository.findByCreditTypeId(typeId).stream().map(creditMapper::toDto).collect(Collectors.toList());
    }

    public CreditDto addCredit(CreditDto creditDto) {
        var credit = creditMapper.fromDto(creditDto);
        creditRepository.save(credit);
        return creditMapper.toDto(credit);
    }

    public CreditDto updateCredit(CreditDto creditDto, UUID id) {
        var credit = creditMapper.fromDto(creditDto);
        var existingCredit = creditRepository.findById(id);

        if (existingCredit.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        credit.setPaidAmount(creditDto.getPaidAmount());
        credit.setStartDate(creditDto.getStartDate());
        credit.setEndDate(creditDto.getEndDate());
        credit.setStatus(creditDto.getStatus());
        var updatedCredit = creditRepository.save(credit);
        return creditMapper.toDto(updatedCredit);
    }

    public void removeCreditById(UUID id) {
        var existingCredit = creditRepository.findById(id);

        if (existingCredit.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditRepository.deleteById(id);
    }

}
