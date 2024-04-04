package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.CreditTypeDto;
import org.system.creditmanagementsystem.dto.RoleDto;
import org.system.creditmanagementsystem.entity.CreditType;
import org.system.creditmanagementsystem.entity.Role;

@Mapper(componentModel = "spring")
public interface CreditTypeMapper {
    CreditTypeDto toDto(CreditType creditType);
    CreditType fromDto(CreditTypeDto creditTypeDto);
}
