package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.UserDto;
import org.system.creditmanagementsystem.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

//    @PostMapping("/users/add") -- будет регистрация?
//    public UserDto addUser(@RequestBody UserDto user) {
//        return userService.addUser(user);
//    }

    @PutMapping("/users/update/{id}")
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable UUID id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/users/delete/{id}")
    public void removeUserById(@PathVariable UUID id) {
        userService.removeUserById(id);
    }
}
