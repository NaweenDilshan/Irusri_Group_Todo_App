package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "email", unique = true)
	private String email;

	private String password;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

