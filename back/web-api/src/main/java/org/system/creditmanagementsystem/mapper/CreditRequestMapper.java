package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.CreditRequestDto;
import org.system.creditmanagementsystem.dto.RoleDto;
import org.system.creditmanagementsystem.entity.CreditRequest;
import org.system.creditmanagementsystem.entity.Role;

@Mapper(componentModel = "spring")
public interface CreditRequestMapper {
    CreditRequestDto toDto(CreditRequest creditRequest);
    CreditRequest fromDto(CreditRequestDto creditRequestDto);
}
