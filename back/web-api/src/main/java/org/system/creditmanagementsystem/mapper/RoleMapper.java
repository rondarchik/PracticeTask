package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.RoleDto;
import org.system.creditmanagementsystem.dto.role.AddRoleDto;
import org.system.creditmanagementsystem.dto.role.GetRoleDto;
import org.system.creditmanagementsystem.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    GetRoleDto toDto(Role role);
    Role fromDto(AddRoleDto roleDto);
//    GetRoleDto toDto(Role role);
//    Role fromDto(AddRoleDto roleDto);
}
