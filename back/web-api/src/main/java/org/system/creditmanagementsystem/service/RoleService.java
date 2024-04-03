package org.system.creditmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.repository.RoleRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private static final String NOT_FOUND_MESSAGE = "Such role not found!";

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    public Role addRole(Role role) {

        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        var existingRole = roleRepository.findById(role.getId());

        if (existingRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        return roleRepository.save(role);
    }

    public void removeRoleById(UUID id) {
        var existingRole = roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        roleRepository.deleteById(id);
    }
}
