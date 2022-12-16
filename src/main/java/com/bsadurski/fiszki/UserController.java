package com.bsadurski.fiszki;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository repo;

    @GetMapping("{userId}")
    public User getUser(@PathVariable int userId) { return repo.getUser(userId);
    }

    @PostMapping
    public int createUser(@Valid @RequestBody User f) {
        return repo.postUser(f);
    }
}
