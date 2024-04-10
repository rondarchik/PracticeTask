package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.RoleDto;
import org.system.creditmanagementsystem.dto.role.AddRoleDto;
import org.system.creditmanagementsystem.dto.role.GetRoleDto;
import org.system.creditmanagementsystem.dto.role.UpdateRoleDto;
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

    public List<GetRoleDto> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).toList();
    }

    public GetRoleDto getRoleById(UUID id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return roleMapper.toDto(role);
    }

    public void addRole(AddRoleDto roleDto) {
//        Role optionalRole = roleRepository.findByRoleName(roleDto.getRoleName());
        Role role = roleMapper.fromDto(roleDto);
        roleRepository.save(role);
    }

    public void updateRole(UpdateRoleDto roleDto, UUID id) {
        Role existingRole = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (roleDto.getRoleName() != null) {
            existingRole.setRoleName(roleDto.getRoleName());
        }
        roleRepository.save(existingRole);
    }

    public void removeRoleById(UUID id) {
        Optional<Role> existingRole = roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        roleRepository.deleteById(id);
    }
}
