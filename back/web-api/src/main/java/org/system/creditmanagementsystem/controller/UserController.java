package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.UserDto;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users/add")
    public UserDto addUser(@RequestBody UserDto user, Set<Role> roles) {
        return userService.addUser(user, roles);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable UUID id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeUserById(@PathVariable UUID id) {
        userService.removeUserById(id);
    }
}
