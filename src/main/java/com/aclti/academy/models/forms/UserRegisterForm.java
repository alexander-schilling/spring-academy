package com.aclti.academy.models.forms;

import com.aclti.academy.enums.ErrorCode;
import com.aclti.academy.models.User;
import com.aclti.academy.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the params for the register form and its validation
 * @author Alexander Schilling
 */
public class UserRegisterForm {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String firstName;
    private String lastName;

    private boolean validated = false;
    private final List<String> errors = new ArrayList<>();

    private void checkEmptyFields() {
        if (username.isBlank()) {
            errors.add(ErrorCode.USERNAME_IS_BLANK.getLabel());
        }

        if (password.isBlank()) {
            errors.add(ErrorCode.PASSWORD_IS_BLANK.getLabel());
        }

        if (firstName.isBlank()) {
            errors.add(ErrorCode.FIRSTNAME_IS_BLANK.getLabel());
        }

        if (lastName.isBlank()) {
            errors.add(ErrorCode.LASTNAME_IS_BLANK.getLabel());
        }
    }

    private void checkPasswordMatch() {
        if (!password.equals(passwordConfirmation)) {
            errors.add(ErrorCode.PASSWORDS_DONT_MATCH.getLabel());
        }
    }

    private void checkIfUserExists(IUserRepository repository) {
        User user = repository.findByUsername(username);

        if (user != null) {
            errors.add(ErrorCode.USER_ALREADY_EXISTS.getLabel());
        }
    }

    /**
     * Validates empty fields, username password match and if user exists,
     * only if previous checks were valid
     * @param repository User repository
     */
    private void validate(IUserRepository repository) {
        checkEmptyFields();
        checkPasswordMatch();
        if (errors.size() == 0) {
            checkIfUserExists(repository);
        }
        validated = true;
    }

    /**
     * Triggers validation if it hasn't run yet, and returns boolean checking errors length
     * @param repository User repository
     * @return True if form has no errors
     */
    public boolean isValid(IUserRepository repository) {
        if (!validated) validate(repository);

        return errors.size() == 0;
    }

    /**
     * Creates a User object with the params of the form
     * @return User object
     */
    public User createUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    // START: Getters & Setters

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPasswordConfirmation() { return passwordConfirmation; }
    public void setPasswordConfirmation(String passwordConfirmation) { this.passwordConfirmation = passwordConfirmation; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public List<String> getErrors() { return errors; }

    // END: Getters & Setters
}
