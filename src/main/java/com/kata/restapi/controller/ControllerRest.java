package com.kata.restapi.controller;

import com.kata.restapi.model.Role;
import com.kata.restapi.model.User;
import com.kata.restapi.service.RoleService;
import com.kata.restapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerRest {

    private final UserService userService;
    private final RoleService roleService;

    public ControllerRest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<User> getUser(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    ResponseEntity<User> newUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    ResponseEntity<User> editUser(@RequestBody User user,
                                  @PathVariable("id") Long id){
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<Long> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/roles")
    ResponseEntity<List<Role>>getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
        return new ResponseEntity<>(roleService.getRole(id), HttpStatus.OK);
    }
}
