package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.CreditRequestDto;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.entity.CreditRequest;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CreditRequestMapper {
    CreditRequestDto toDto(CreditRequest creditRequest);
    CreditRequest fromDto(CreditRequestDto creditRequestDto);

    default CreditRequest mapCreditRequest(UUID id) {
        return null;
    }

    default UUID map(Credit credit) {
        return credit.getId();
    }

    default Credit mapToCredit(UUID creditId) {
        return null;
    }
}
