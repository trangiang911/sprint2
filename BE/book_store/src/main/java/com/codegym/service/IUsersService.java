package com.codegym.service;

import com.codegym.model.Users;


import java.util.List;

public interface IUsersService {

    void saveUser(Users users);

    List<Users> checkEmail(String email);
}
