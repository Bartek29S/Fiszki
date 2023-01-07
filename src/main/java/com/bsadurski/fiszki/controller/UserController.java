package com.bsadurski.fiszki.controller;


import com.bsadurski.fiszki.dto.LoginDto;
import com.bsadurski.fiszki.entity.User;
import com.bsadurski.fiszki.repository.UserRepository;
import com.bsadurski.fiszki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("{userId}")
    public User getUser(@PathVariable int userId) {
        return service.getUser(userId);
    }

    @PostMapping("/register")
    public int register(@Valid @RequestBody User user) {

        return service.postUser(user);
    }


}
