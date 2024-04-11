package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.type.AddCreditTypeDto;
import org.system.creditmanagementsystem.dto.type.GetCreditTypeDto;
import org.system.creditmanagementsystem.entity.CreditType;

@Mapper(componentModel = "spring")
public interface CreditTypeMapper {
    GetCreditTypeDto toDto(CreditType creditType);
    CreditType fromDto(AddCreditTypeDto creditTypeDto);
}
