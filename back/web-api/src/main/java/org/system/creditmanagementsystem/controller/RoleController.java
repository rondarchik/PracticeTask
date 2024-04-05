package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.RoleDto;
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
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable UUID id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/add")
    public RoleDto addRole(@RequestBody RoleDto role) {
        return roleService.addRole(role);
    }

    @PutMapping("/update/{id}")
    public RoleDto updateRole(@RequestBody RoleDto role, @PathVariable UUID id) {
        return roleService.updateRole(role, id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeRoleById(@PathVariable UUID id) {
        roleService.removeRoleById(id);
    }
}
