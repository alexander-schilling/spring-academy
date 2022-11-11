package com.aclti.academy.controllers;

import com.aclti.academy.models.User;
import com.aclti.academy.models.forms.UserLoginForm;
import com.aclti.academy.models.forms.UserRegisterForm;
import com.aclti.academy.repositories.IUserRepository;
import com.aclti.academy.services.EncoderService;
import com.aclti.academy.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controls users in app and routes logic
 * @author Alexander Schilling
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserRepository repository;
    private final EncoderService encoderService;
    private final TokenService tokenService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(IUserRepository repository, EncoderService encoderService, TokenService tokenService) {
        this.repository = repository;
        this.encoderService = encoderService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public List<User> index() {
        return repository.findAll();
    }

    /**
     * Answers the /users/register route with a JSON response representing an error
     * or User data with a created User and a generated authentication token
     * @param userRegisterForm Params representing a UserRegisterForm
     * @return ResponseEntity with registered user if successful
     */
    @PutMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterForm userRegisterForm) {
        boolean isValid = userRegisterForm.isValid(repository);

        if (!isValid) {
             return new ResponseEntity<>(userRegisterForm, HttpStatus.BAD_REQUEST);
        }

        User user = userRegisterForm.createUser();

        // Create the new user with authentication token
        user.setPassword(encoderService.encodePassword(user.getPassword()));
        user.setToken(tokenService.generateToken(user.getUsername()));

        return new ResponseEntity<>(repository.save(user), HttpStatus.OK);
    }

    /**
     * Answers the /users/login route with a JSON response representing an error
     * or User data with a generated authentication token
     * @param userLoginForm Params representing a UserLoginForm
     * @return ResponseEntity with logged user if successful
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginForm userLoginForm) {
        boolean isValid = userLoginForm.isValid(repository, encoderService);

        if (!isValid) {
            return new ResponseEntity<>(userLoginForm, HttpStatus.BAD_REQUEST);
        }

        User user = userLoginForm.getUser();

        // If login is successful, generate a token to be used in user requests
        user.setToken(tokenService.generateToken(user.getUsername()));
        repository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
