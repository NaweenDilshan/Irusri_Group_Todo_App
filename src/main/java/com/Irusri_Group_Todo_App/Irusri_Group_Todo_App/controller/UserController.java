package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.controller;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.User;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
