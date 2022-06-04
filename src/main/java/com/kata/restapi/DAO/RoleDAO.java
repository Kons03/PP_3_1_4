package com.kata.restapi.DAO;

import com.kata.restapi.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();

    void saveRole(Role role);

    Role getRole(Long id);

    Role getRoleByName(String name);

    void deleteRole(Long id);
}
