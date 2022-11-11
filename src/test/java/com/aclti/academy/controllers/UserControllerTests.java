package com.aclti.academy.controllers;

import com.aclti.academy.models.forms.UserRegisterForm;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTests extends AbstractControllerTest {
    public static String baseUrl = "/api/users";

    @Override
    @Before("")
    public void setUp() {
        super.setUp();
    }

    @Test
    public void successfulRegisterTest() throws Exception {
        String uri = String.format("%s/register", baseUrl);

        UserRegisterForm userRegisterForm = new UserRegisterForm();

        userRegisterForm.setUsername("test_user");
        userRegisterForm.setPassword("test_password");
        userRegisterForm.setPasswordConfirmation("test_password");
        userRegisterForm.setFirstName("Test");
        userRegisterForm.setLastName("User");

        String inputJson = super.mapToJson(userRegisterForm);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
