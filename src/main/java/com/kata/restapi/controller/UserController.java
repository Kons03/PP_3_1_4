package com.kata.restapi.controller;

import com.kata.restapi.database.DataBaseInit;
import com.kata.restapi.model.User;
import com.kata.restapi.service.RoleService;
import com.kata.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, DataBaseInit init) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getSinglePage(@AuthenticationPrincipal User authUser, Model model) {
        model.addAttribute("authUser", authUser);
        return "users";
    }

}