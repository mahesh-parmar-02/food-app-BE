package com.assigment.inventory_management.controllers;

import com.assigment.inventory_management.models.User;
import com.assigment.inventory_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequest userRequest) {
        User user = userService.registerUser(userRequest.getEmail(), userRequest.getPassword());
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserRequest> authenticateUser(@RequestBody UserRequest userRequest) {
        User user = userService.authenticateUser(userRequest.getEmail(), userRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok(userRequest);
        }
        return ResponseEntity.status(401).build();
    }

    // Define UserRequest class to map JSON body
    static class UserRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
