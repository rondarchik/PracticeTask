package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.request.AddCreditRequestDto;
import org.system.creditmanagementsystem.dto.request.GetCreditRequestDto;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.entity.CreditRequest;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CreditRequestMapper {
    GetCreditRequestDto toDto(CreditRequest creditRequest);
    CreditRequest fromDto(AddCreditRequestDto creditRequestDto);

    default UUID mapCredit(Credit credit) {
        return credit.getId();
    }
}
