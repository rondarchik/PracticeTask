package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.type.AddCreditTypeDto;
import org.system.creditmanagementsystem.dto.type.GetCreditTypeDto;
import org.system.creditmanagementsystem.dto.type.UpdateCreditTypeDto;
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

    public List<GetCreditTypeDto> getAllCreditTypes() {
        return creditTypeRepository.findAll().stream().map(creditTypeMapper::toDto).toList();
    }

    public GetCreditTypeDto getCreditTypeById(UUID id) {
        CreditType creditType = creditTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return creditTypeMapper.toDto(creditType);
    }

    public GetCreditTypeDto addCreditType(AddCreditTypeDto creditTypeDto) {
        CreditType creditType = creditTypeMapper.fromDto(creditTypeDto);
        creditTypeRepository.save(creditType);
        return creditTypeMapper.toDto(creditType);
    }

    public GetCreditTypeDto updateCreditType(UpdateCreditTypeDto creditTypeDto, UUID id) {
        CreditType existingCreditType = creditTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (creditTypeDto.getName() != null) {
            existingCreditType.setName(creditTypeDto.getName());
        }
        if (creditTypeDto.getCreditAmount() != null) {
            existingCreditType.setCreditAmount(creditTypeDto.getCreditAmount());
        }
        if (creditTypeDto.getInterestRate() != null) {
            existingCreditType.setInterestRate(creditTypeDto.getInterestRate());
        }
        if (creditTypeDto.getTermInMonths() != 0) {
            existingCreditType.setTermInMonths(creditTypeDto.getTermInMonths());
        }

        creditTypeRepository.save(existingCreditType);
        return creditTypeMapper.toDto(existingCreditType);
    }

    public void removeCreditTypeById(UUID id) {
        Optional<CreditType> existingCreditType = creditTypeRepository.findById(id);
        if (existingCreditType.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        creditTypeRepository.deleteById(id);
    }
}
