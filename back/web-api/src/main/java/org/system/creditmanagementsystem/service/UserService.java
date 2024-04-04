package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.UserDto;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.entity.User;
import org.system.creditmanagementsystem.exception.AlreadyExistsException;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.UserMapper;
import org.system.creditmanagementsystem.repository.RoleRepository;
import org.system.creditmanagementsystem.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private static final String NOT_FOUND_MESSAGE = "Such user not found!";
    private static final String CONFLICT = "Such record already exists in user.";

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList()); // или просто toList()?
    }

    public UserDto getUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return userMapper.toDto(user);
    }

    public UserDto findUserByEmail(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return userMapper.toDto(user);
    }

    public UserDto addUser(UserDto userDto, String roleName) {
        var optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new AlreadyExistsException(CONFLICT);
        }

        var user = userMapper.fromDto(userDto);
        var role = roleRepository.findByName(roleName).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        user.setRoles((Set<Role>) role);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto updateUser(UserDto userDto, UUID id) {
        var user = userMapper.fromDto(userDto);
        var existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        var updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    public void removeUserById(UUID id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        userRepository.deleteById(id);
    }
}
