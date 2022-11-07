package com.aclti.academy.controllers;

import com.aclti.academy.models.User;
import com.aclti.academy.repositories.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    private final IUserRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(IUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private List<User> index() {
        return repository.findAll();
    }

    @PutMapping("/register")
    private User register(@RequestBody User user) {
        logger.info("Attempting to register user " + user.toString());
        return repository.save(user);
    }
}
