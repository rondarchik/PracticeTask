package org.system.creditmanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.service.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        var roles = roleService.getRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable UUID id) {
        var role = roleService.getRoleById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<Object> addRole(Role role) {
        roleService.addRole(role);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/roles")
    public ResponseEntity<Object> updateRole(Role role) {
        roleService.updateRole(role);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Object> removeRoleById(@PathVariable UUID id) {
        roleService.removeRoleById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
