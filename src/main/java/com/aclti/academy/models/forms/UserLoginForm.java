package com.aclti.academy.models.forms;

import com.aclti.academy.enums.ErrorCode;
import com.aclti.academy.models.User;
import com.aclti.academy.repositories.IUserRepository;
import com.aclti.academy.services.EncoderService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the params for the login form and its validation
 * @author Alexander Schilling
 */
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

    /**
     * Validates with the raw password and the encrypted password with the encoder service
     * @param repository User repository
     * @param encoderService Encoder service
     */
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

    /**
     * Validates empty fields and username password match
     * @param repository User repository
     * @param encoderService Encoder service
     */
    private void validate(IUserRepository repository, EncoderService encoderService) {
        checkEmptyFields();
        checkUsernameAndPassword(repository, encoderService);
        validated = true;
    }

    /**
     * Triggers validation if it hasn't run yet, and returns boolean checking errors length
     * @param repository User repository
     * @param encoderService Encoder service
     * @return True if form has no errors
     */
    public boolean isValid(IUserRepository repository, EncoderService encoderService) {
        if (!validated) validate(repository, encoderService);

        return errors.size() == 0;
    }

    // START: Getters & Setters

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public User getUser() { return user; }
    public List<String> getErrors() { return errors; }

    // END: Getters & Setters
}
