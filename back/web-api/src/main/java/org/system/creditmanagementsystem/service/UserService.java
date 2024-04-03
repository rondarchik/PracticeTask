package org.system.creditmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.entity.User;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String NOT_FOUND_MESSAGE = "Such user not found!";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        var existingUser = userRepository.findById(user.getId());

        if (existingUser.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        userRepository.save(user);
    }

    public void removeUserById(UUID id) {
        var existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        userRepository.deleteById(id);
    }
}
