package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.role.AddRoleDto;
import org.system.creditmanagementsystem.dto.role.GetRoleDto;
import org.system.creditmanagementsystem.dto.role.UpdateRoleDto;
import org.system.creditmanagementsystem.service.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin("http://localhost:3000/")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<GetRoleDto>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRoleDto> getRoleById(@PathVariable UUID id) {
        return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GetRoleDto> addRole(@RequestBody AddRoleDto role) {
        return new ResponseEntity<>(roleService.addRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetRoleDto> updateRole(@RequestBody UpdateRoleDto role, @PathVariable UUID id) {
        return new ResponseEntity<>(roleService.updateRole(role, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeRoleById(@PathVariable UUID id) {
        roleService.removeRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{roleId}/users/{userId}")
    public ResponseEntity<Object> removeUserFromRole(@PathVariable UUID roleId, @PathVariable UUID userId) {
        roleService.removeUserFromRole(roleId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
