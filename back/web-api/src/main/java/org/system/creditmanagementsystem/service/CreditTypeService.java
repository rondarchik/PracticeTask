package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.CreditTypeDto;
import org.system.creditmanagementsystem.entity.CreditType;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.CreditTypeMapper;
import org.system.creditmanagementsystem.repository.CreditTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditTypeService {
    private final CreditTypeRepository creditTypeRepository;
    private final CreditTypeMapper creditTypeMapper;
    private static final String NOT_FOUND_MESSAGE = "Such credit type not found!";

    @Autowired
    public CreditTypeService(CreditTypeRepository creditTypeRepository, CreditTypeMapper creditTypeMapper) {
        this.creditTypeRepository = creditTypeRepository;
        this.creditTypeMapper = creditTypeMapper;
    }

    public List<CreditTypeDto> getAllCreditTypes() {
        return creditTypeRepository.findAll().stream().map(creditTypeMapper::toDto).toList();
    }

    public CreditTypeDto getCreditTypeById(UUID id) {
        CreditType creditType = creditTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return creditTypeMapper.toDto(creditType);
    }

    public CreditTypeDto addCreditType(CreditTypeDto creditTypeDto) {
        CreditType creditType = creditTypeMapper.fromDto(creditTypeDto);
        creditTypeRepository.save(creditType);
        return creditTypeMapper.toDto(creditType);
    }

    public CreditTypeDto updateCreditType(CreditTypeDto creditTypeDto, UUID id) {
        CreditType creditType = creditTypeMapper.fromDto(creditTypeDto);
        Optional<CreditType> existingCreditType = creditTypeRepository.findById(id);

        if (existingCreditType.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditType.setName(creditTypeDto.getName());
        creditType.setCreditAmount(creditTypeDto.getCreditAmount());
        creditType.setInterestRate(creditTypeDto.getInterestRate());
        creditType.setTermInMonths(creditTypeDto.getTermInMonths());
        CreditType updatedCreditType = creditTypeRepository.save(creditType);
        return creditTypeMapper.toDto(updatedCreditType);
    }

    public void removeCreditTypeById(UUID id) {
        Optional<CreditType> existingCreditType = creditTypeRepository.findById(id);

        if (existingCreditType.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditTypeRepository.deleteById(id);
    }
}
