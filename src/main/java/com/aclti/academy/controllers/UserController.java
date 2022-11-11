package com.aclti.academy.controllers;

import com.aclti.academy.models.User;
import com.aclti.academy.models.forms.UserLoginForm;
import com.aclti.academy.models.forms.UserRegisterForm;
import com.aclti.academy.repositories.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final IUserRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(IUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> index() {
        return repository.findAll();
    }

    @PutMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterForm userRegisterForm) {
        boolean isValid = userRegisterForm.isValid(repository);

        if (!isValid) {
             return new ResponseEntity<>(userRegisterForm, HttpStatus.BAD_REQUEST);
        }

        User user = userRegisterForm.createUser();
        logger.info("Attempting to register user " + user.toString());
        return new ResponseEntity<>(repository.save(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginForm userLoginForm) {
        boolean isValid = userLoginForm.isValid(repository);

        if (!isValid) {
            return new ResponseEntity<>(userLoginForm, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userLoginForm.getUser(), HttpStatus.OK);
    }
}
