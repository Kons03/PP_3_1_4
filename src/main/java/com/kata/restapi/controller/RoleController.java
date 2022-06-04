package com.kata.restapi.controller;

import com.kata.restapi.database.DataBaseInit;
import com.kata.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RoleController {
    private DataBaseInit init;
    private UserService userService;

    @Autowired
    public RoleController(DataBaseInit init, UserService userService) {
        this.init = init;
        this.userService = userService;
    }

    @GetMapping("")
    public String rootBoot(){
        if (userService.getAllUsers().isEmpty()){
            init.addUsersToDB();
        }
        return "redirect:/login";
    }
}
