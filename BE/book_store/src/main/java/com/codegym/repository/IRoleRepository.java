package com.codegym.repository;


import com.codegym.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRoleRepository extends JpaRepository<Roles,Integer> {
    @Query(value="select * from roles where role_name = :roleName",nativeQuery=true)
    Roles findRolesByRoleName(@Param("roleName") String string);
}
