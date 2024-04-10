package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.user.AddUserDto;
import org.system.creditmanagementsystem.dto.user.GetUserDto;
import org.system.creditmanagementsystem.dto.user.UpdateUserDto;
import org.system.creditmanagementsystem.entity.User;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.UserMapper;
import org.system.creditmanagementsystem.repository.RoleRepository;
import org.system.creditmanagementsystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<GetUserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public GetUserDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return userMapper.toDto(user);
    }


    public GetUserDto addUser(AddUserDto userDto) {
        User user = userMapper.fromDto(userDto);
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
