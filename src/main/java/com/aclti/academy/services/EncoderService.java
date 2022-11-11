package com.aclti.academy.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService {
    private final BCryptPasswordEncoder encoder;

    public EncoderService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encodePassword(String password) {
        return encoder.encode(password);
    }

    public boolean matchesPasswords(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
