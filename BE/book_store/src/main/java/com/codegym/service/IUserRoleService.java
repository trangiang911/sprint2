package com.codegym.service;

import com.codegym.model.UserRole;

public interface IUserRoleService {
    UserRole findUserRole(String roleName);

    void saveUserRole(UserRole userRole);

}
