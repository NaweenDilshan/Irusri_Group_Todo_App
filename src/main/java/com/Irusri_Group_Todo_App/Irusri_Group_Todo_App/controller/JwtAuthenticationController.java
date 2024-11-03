package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.controller;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.request.JwtRequest;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.response.CommonResponse;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.response.JwtResponse;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.User;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service.JwtUserDetailsService;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class JwtAuthenticationController {


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword()); // Changed to email
		final UserDetails userDetails = userDetailsService.loadUserByEmail(authenticationRequest.getEmail()); // Changed to load by email
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token, true));
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			System.out.print("user disabled");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.print("invalid credentials");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}



	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<CommonResponse<Integer>> saveUser(@RequestBody User user) throws Exception {
		int result = userDetailsService.save(user);
		String message = (result > 0) ? "User registered successfully." : "User registration failed.";

		return ResponseEntity.ok(CommonResponse.<Integer>builder()
				.isSuccess(result > 0)
				.dataBundle(result)
				.message(message)
				.build());
	}




}
