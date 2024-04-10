package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.user.AddUserDto;
import org.system.creditmanagementsystem.dto.user.GetUserDto;
import org.system.creditmanagementsystem.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
    GetUserDto toDto(User user);
    User fromDto(AddUserDto userDto);
}
