package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service;

import java.util.ArrayList;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.dao.UserDao;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;



@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	// Load user by email
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		User user = userDao.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}

	// Reuse the email loading logic
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return loadUserByEmail(email);
	}


	public int save(User user) {
		User newUser = new User(user.getEmail(), bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}
}
