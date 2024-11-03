package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.repository;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // JpaRepository provides CRUD operations out of the box
}
