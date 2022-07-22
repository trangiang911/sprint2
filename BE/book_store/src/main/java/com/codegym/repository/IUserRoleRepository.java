package com.codegym.repository;

import com.codegym.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IUserRoleRepository extends JpaRepository<UserRole,Integer> {

    @Query(value="insert into user_role(username,role)values (:email,:i)",nativeQuery=true)
    @Transactional
    @Modifying
    void saveUserRole(@Param("email") String email, @Param("i") int i);

    @Query(value="select *  from user_role where username = :username",nativeQuery = true)
    UserRole findUserRoleByUsername(@Param("username") String username);
}
