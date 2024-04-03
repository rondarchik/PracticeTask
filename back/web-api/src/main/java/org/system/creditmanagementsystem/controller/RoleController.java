package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.service.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public Role getRoleById(@PathVariable UUID id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/roles/add")
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @PutMapping("/roles/update")
    public Role updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/roles/delete/{id}")
    public void removeRoleById(@PathVariable UUID id) {
        roleService.removeRoleById(id);
    }
}
