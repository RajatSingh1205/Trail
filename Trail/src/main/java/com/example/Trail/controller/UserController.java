package com.example.Trail.controller;

import com.example.Trail.entity.User;
import com.example.Trail.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/addUser")
    public ResponseEntity<?> userRegistration(@RequestBody User user) {
        User savedUser = userServices.addUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUser() {
        List<User> users = userServices.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        Optional<User> user = userServices.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
