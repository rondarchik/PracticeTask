package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.credit.AddCreditDto;
import org.system.creditmanagementsystem.dto.credit.GetCreditDto;
import org.system.creditmanagementsystem.entity.Credit;

@Mapper(componentModel = "spring")
public interface CreditMapper {
    GetCreditDto toDto(Credit credit);
    Credit fromDto(AddCreditDto creditDto);
}
