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
import java.util.Optional;
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

    public List<UserDto> getAllUsersWithRoles() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserDto userDto = userMapper.toDto(user);
            userDto.setRoles(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet()));
            return userDto;
        }).toList();
    }


    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return userMapper.toDto(user);
    }

    public UserDto addUser(UserDto userDto, Set<Role> roles) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new AlreadyExistsException(CONFLICT);
        }

        User user = userMapper.fromDto(userDto);
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto updateUser(UserDto userDto, UUID id) {
        User user = userMapper.fromDto(userDto);
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setPasswordHash(user.getPasswordHash());
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    public void removeUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        userRepository.deleteById(id);
    }

    public Set<Role> getUserRoles(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return roleRepository.findByUsers_Id(user.getId());
    }
}
