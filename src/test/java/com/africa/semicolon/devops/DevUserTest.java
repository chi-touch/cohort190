package com.africa.semicolon.devops;

import com.africa.semicolon.devops.dto.requests.RegisterRequest;
import com.africa.semicolon.devops.dto.responses.RegisterResponse;
import com.africa.semicolon.devops.repositories.UserRepository;
import com.africa.semicolon.devops.services.UserService;
import com.africa.semicolon.devops.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DevUserTest {

    @Autowired
   private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;




    @Test
    public void testToRegisterUser(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("email@example.com");
        registerRequest.setFullName("myName");
        registerRequest.setPassword("pass123");
        registerRequest.setUserName("myName");

        RegisterResponse response = userService.register(registerRequest);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("Registered Successful"));
        assertEquals(1, userRepository.findAll().size());
    }
}
