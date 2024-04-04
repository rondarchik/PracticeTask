package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.CreditDto;
import org.system.creditmanagementsystem.dto.RoleDto;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.entity.Role;

@Mapper(componentModel = "spring")
public interface CreditMapper {
    CreditDto toDto(Credit credit);
    Credit fromDto(CreditDto creditDto);
}
