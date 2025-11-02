package com.revature.revagenda_server.controllers;

import com.revature.revagenda_server.models.User;
import com.revature.revagenda_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> hardCodedList = new ArrayList<>();
        hardCodedList.add(new User(1, "kplu", "password123", "Kyle", "Plummer"));
        return hardCodedList;
    }

}
