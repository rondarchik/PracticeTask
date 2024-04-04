package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.RoleDto;
import org.system.creditmanagementsystem.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role fromDto(RoleDto roleDto);
}
