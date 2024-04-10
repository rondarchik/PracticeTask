package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.user.AddUserDto;
import org.system.creditmanagementsystem.dto.user.GetUserDto;
import org.system.creditmanagementsystem.dto.user.UpdateUserDto;
import org.system.creditmanagementsystem.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<GetUserDto>> getAllUsersWithRoles() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetUserDto> getUserById(@PathVariable UUID id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GetUserDto> addUser(@RequestBody AddUserDto user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetUserDto> updateUser(@RequestBody UpdateUserDto user, @PathVariable UUID id) {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeUserById(@PathVariable UUID id) {
        userService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
