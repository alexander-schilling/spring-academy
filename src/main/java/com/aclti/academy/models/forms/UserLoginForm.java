package com.aclti.academy.models.forms;

import com.aclti.academy.enums.ErrorCode;
import com.aclti.academy.models.User;
import com.aclti.academy.repositories.IUserRepository;
import com.aclti.academy.services.EncoderService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class UserLoginForm {
    private String username;
    private String password;
    private boolean validated = false;
    private final List<String> errors = new ArrayList<>();
    @JsonIgnore
    private User user;

    private User findUser(IUserRepository repository) {
        return repository.findByUsername(getUsername());
    }

    private void checkEmptyFields() {
        if (getUsername().isBlank()) {
            getErrors().add(ErrorCode.USERNAME_IS_BLANK.getLabel());
        }

        if (getPassword().isBlank()) {
            getErrors().add(ErrorCode.PASSWORD_IS_BLANK.getLabel());
        }
    }

    private void checkUsernameAndPassword(IUserRepository repository, EncoderService encoderService) {
        user = findUser(repository);

        if (user == null) {
            errors.add(ErrorCode.USER_NOT_FOUND.getLabel());
            return;
        }

        if (!encoderService.matchesPasswords(password, user.getPassword())) {
            errors.add(ErrorCode.USER_WRONG_PASSWORD.getLabel());
            return;
        }
    }

    private void validate(IUserRepository repository, EncoderService encoderService) {
        checkEmptyFields();
        checkUsernameAndPassword(repository, encoderService);
        validated = true;
    }

    public boolean isValid(IUserRepository repository, EncoderService encoderService) {
        if (!validated) validate(repository, encoderService);

        return errors.size() == 0;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public User getUser() { return user; }
    public List<String> getErrors() { return errors; }
}
