package com.codegym.service.impl;


import com.codegym.model.UserRole;
import com.codegym.repository.IUserRoleRepository;
import com.codegym.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService {
    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Override
    public UserRole findUserRole(String roleName) {
        return this.iUserRoleRepository.findUserRoleByUsername(roleName);
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        this.iUserRoleRepository.save(userRole);
    }

}
