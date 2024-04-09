package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.user.AddUserDto;
import org.system.creditmanagementsystem.dto.user.UserDto;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User fromDto(AddUserDto userDto);
//    User fromDto(UserDto userDto);

    default Set<String> map(Set<Role> roles) {
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
    }

}
