package com.codegym.repository;



import com.codegym.dto.user.IUsersDto;
import com.codegym.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface IUsersRepository extends JpaRepository<Users,String> {

    @Query(value="SELECT username,password,flag FROM Users where username = :username and password = :password",nativeQuery=true)
    @Transactional
    @Modifying
    IUsersDto getUsersByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value="select * from users u where exists (select * from users where u.username = :email)",nativeQuery=true)
    List<Users> checkEmail(@Param("email") String email);

    @Query(value="insert into users(username,password,flag) values (:username,:password,:flag)", nativeQuery=true)
    @Transactional
    @Modifying
    void saveUser(@Param("username") String username, @Param("password") String password, @Param("flag") boolean flag);

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Users getUserByUsername(@Param("username") String username);
}
