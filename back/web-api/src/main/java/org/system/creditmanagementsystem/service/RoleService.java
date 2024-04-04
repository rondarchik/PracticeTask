package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.RoleDto;
import org.system.creditmanagementsystem.exception.AlreadyExistsException;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.RoleMapper;
import org.system.creditmanagementsystem.repository.RoleRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toList()); // или просто toList()?
    }

    public RoleDto getRoleById(UUID id) {
        var role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return roleMapper.toDto(role);
    }

    public RoleDto addRole(RoleDto roleDto) {
        var optionalRole = roleRepository.findByName(roleDto.getRoleName());

        if (optionalRole.isPresent()) {
            throw new AlreadyExistsException(CONFLICT);
        }

        var role = roleMapper.fromDto(roleDto);
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    public RoleDto updateRole(RoleDto roleDto) {
        var role = roleMapper.fromDto(roleDto);
        var existingRole = roleRepository.findById(role.getId());

        if (existingRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        role.setRoleName(roleDto.getRoleName());
        var updatedRole = roleRepository.save(role);
        return roleMapper.toDto(updatedRole);
    }

    public void removeRoleById(UUID id) {
        var existingRole = roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        roleRepository.deleteById(id);
    }
}
