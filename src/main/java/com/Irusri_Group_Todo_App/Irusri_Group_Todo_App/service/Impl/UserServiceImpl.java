package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service.Impl;


import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.User;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.repository.UserRepository;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

