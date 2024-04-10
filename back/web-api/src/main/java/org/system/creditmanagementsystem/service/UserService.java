package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.user.AddUserDto;
import org.system.creditmanagementsystem.dto.user.GetUserDto;
import org.system.creditmanagementsystem.dto.user.UpdateUserDto;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.entity.User;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.UserMapper;
import org.system.creditmanagementsystem.repository.RoleRepository;
import org.system.creditmanagementsystem.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private static final String NOT_FOUND_MESSAGE = "Such user not found!";

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    public List<GetUserDto> getAllUsers() {
//        return userRepository.findAll().stream().map(userMapper::toDto).toList();
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            GetUserDto userDto = userMapper.toDto(user);
            userDto.setRoles(user.getRoles());
            return userDto;
        }).toList();
    }

    public GetUserDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return userMapper.toDto(user);
    }

    public List<GetUserDto> getRoleUsers(UUID roleId) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        Role role = optionalRole.get();
        roles.add(role);
        return userRepository.findByRolesContains(roles).stream().map(userMapper::toDto).toList();
    }

    public GetUserDto addUser(AddUserDto userDto) {
        User user = userMapper.fromDto(userDto);
        Set<Role> roles = new HashSet<>();
        if (!userDto.getRoles().isEmpty()) {
            for (Role role : userDto.getRoles()) {
                Role existingRole = roleRepository.findById(role.getId()).orElse(null);
                if (existingRole != null) {
                    existingRole.getUsers().add(user);
                    roles.add(existingRole);
                }
            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public GetUserDto updateUser(UpdateUserDto userDto, UUID id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (userDto.getName() != null) {
            existingUser.setName(userDto.getName());
        }
        if (userDto.getSurname() != null) {
            existingUser.setSurname(userDto.getSurname());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getBirthDate() != null) {
            existingUser.setBirthDate(userDto.getBirthDate());
        }
        if (userDto.getPasswordHash() != null) {
            existingUser.setPasswordHash(userDto.getPasswordHash());
        }
        if (userDto.getRoles() != null) {
            Set<Role> newRoles = new HashSet<>();
            for (Role role : userDto.getRoles()) {
                Role existingRole = roleRepository.findById(role.getId()).orElse(null);
                if (existingRole != null) {
                    existingRole.getUsers().add(existingUser);
                    newRoles.add(existingRole);
                }
            }
            existingUser.setRoles(newRoles);
        }
        userRepository.save(existingUser);
        return userMapper.toDto(existingUser);
    }

    public void removeUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        userRepository.deleteById(id);
    }
}
