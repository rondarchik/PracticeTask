package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.CreditRequestDto;
import org.system.creditmanagementsystem.entity.CreditRequest;

@Mapper(componentModel = "spring")
public interface CreditRequestMapper {
    CreditRequestDto toDto(CreditRequest creditRequest);
    CreditRequest fromDto(CreditRequestDto creditRequestDto);
}
