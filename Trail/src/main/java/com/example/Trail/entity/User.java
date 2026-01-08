package com.example.Trail.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

//    user id
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

//    first name
    @Column(name = "First_Name")
    private String firstName;

//    last name
    @Column(name = "Last_Name")
    private String lastName;

//    username
    @Column(name = "Username")
    private String username;

//    email
    @Column(name = "email")
    private String emailId;

//    password
    @Column(name = "password")
    private String password;

}
