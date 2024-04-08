package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.RoleDto;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.exception.AlreadyExistsException;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.RoleMapper;
import org.system.creditmanagementsystem.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final String NOT_FOUND_MESSAGE = "Such role not found!";
    private static final String CONFLICT = "Such record already exists in role.";

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).toList();
    }

    public RoleDto getRoleById(UUID id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return roleMapper.toDto(role);
    }

    public RoleDto addRole(RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.findByRoleName(roleDto.getRoleName());

        if (optionalRole.isPresent()) {
            throw new AlreadyExistsException(CONFLICT);
        }

        Role role = roleMapper.fromDto(roleDto);
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    public RoleDto updateRole(RoleDto roleDto, UUID id) {
        Role role = roleMapper.fromDto(roleDto);
        Optional<Role> existingRole = roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        role.setRoleName(roleDto.getRoleName());
        Role updatedRole = roleRepository.save(role);
        return roleMapper.toDto(updatedRole);
    }

    public void removeRoleById(UUID id) {
        Optional<Role> existingRole = roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        roleRepository.deleteById(id);
    }
}
