package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service;


import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
}
