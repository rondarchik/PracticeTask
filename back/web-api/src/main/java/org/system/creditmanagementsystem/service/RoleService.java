package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.role.AddRoleDto;
import org.system.creditmanagementsystem.dto.role.GetRoleDto;
import org.system.creditmanagementsystem.dto.role.UpdateRoleDto;
import org.system.creditmanagementsystem.entity.Role;
import org.system.creditmanagementsystem.entity.User;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.RoleMapper;
import org.system.creditmanagementsystem.repository.RoleRepository;
import org.system.creditmanagementsystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;
    private static final String NOT_FOUND_MESSAGE = "Such role not found!";

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleMapper = roleMapper;
    }

    public List<GetRoleDto> getAllRoles() {
        List<Role> roles =  roleRepository.findAll();
        return roles.stream().map(role -> {
            GetRoleDto roleDto = roleMapper.toDto(role);
            roleDto.setUsers(getUsersByRoleId(role.getId()));
            return roleDto;
        }).toList();
    }

    public GetRoleDto getRoleById(UUID id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return roleMapper.toDto(role);
    }

    public Set<User> getUsersByRoleId(UUID id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return role.getUsers();
    }

    public GetRoleDto addRole(AddRoleDto roleDto) {
        Role role = roleMapper.fromDto(roleDto);
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    public GetRoleDto updateRole(UpdateRoleDto roleDto, UUID id) {
        Role existingRole = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (roleDto.getRoleName() != null) {
            existingRole.setRoleName(roleDto.getRoleName());
        }
        roleRepository.save(existingRole);
        return roleMapper.toDto(existingRole);
    }

    public void removeRoleById(UUID id) {
        Optional<Role> existingRole = roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        roleRepository.deleteById(id);
    }

    public void removeUserFromRole(UUID roleId, UUID userId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Such user not found!"));
        role.getUsers().remove(user);
        roleRepository.save(role);
    }
}
