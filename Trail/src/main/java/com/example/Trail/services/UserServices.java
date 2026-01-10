package com.example.Trail.services;

import com.example.Trail.entity.User;
import com.example.Trail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
       return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userUd) {
        return userRepository.findById(userUd);
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }


}
