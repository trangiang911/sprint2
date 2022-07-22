package com.codegym.service.impl;


import com.codegym.model.Roles;
import com.codegym.repository.IRoleRepository;
import com.codegym.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Roles findRoleByName(String roleUser) {
        return this.iRoleRepository.findRolesByRoleName(roleUser);

    }
}
