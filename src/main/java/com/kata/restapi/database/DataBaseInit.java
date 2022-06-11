package com.kata.restapi.database;

import com.kata.restapi.model.Role;
import com.kata.restapi.model.User;
import com.kata.restapi.service.UserService;
import com.kata.restapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class DataBaseInit {

    private final UserService userService;
    private final RoleService roleService;
    Set<Role> defaultRoles;

    @Autowired
    public DataBaseInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct(){
        addRolesToDB();
        addUsersToDB();
    }

    private void addRolesToDB(){
        List<Role> roleList = null;
        try {
            roleList = roleService.getAllRoles();
        } catch (Exception ignore) {
        }

        if (roleList == null || roleList.isEmpty()) {
            for (StandardRoles role : StandardRoles.values()) {
                roleService.saveRole(new Role(role));
            }
        }
    }

    public void addUsersToDB(){

        Role roleUser = roleService.getRoleByName(StandardRoles.ROLE_USER.name());
        Role roleAdmin = roleService.getRoleByName(StandardRoles.ROLE_ADMIN.name());

        userService.saveUsers(
                new User("Sid", "sid_h", "sid123",
                        "89005001020", "sid@sexpistols.com")
                        .addRolesToUser(roleUser, roleAdmin),

                new User("Misha", "krug", "misha123",
                        "89001002030", "mishakrug@zeka.ru")
                        .addRoleToUser(roleUser),

                new User("Alla", "pugacheva", "alla123",
                "89132319077", "allapugach@star.com")
                        .addRoleToUser(roleAdmin));
    }

    @Bean
    public Set<Role> getDefaultRoles() {
        if (defaultRoles == null || defaultRoles.isEmpty()) {
            defaultRoles = new HashSet<>(roleService.getAllRoles());
        }
        return defaultRoles;
    }
}