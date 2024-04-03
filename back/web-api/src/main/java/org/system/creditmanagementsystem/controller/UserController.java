package org.system.creditmanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.User;
import org.system.creditmanagementsystem.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        var users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        var user = userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(User user) {
        userService.addUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(User user) {
        userService.updateUser(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> removeUserById(@PathVariable UUID id) {
        userService.removeUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
