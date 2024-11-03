package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final Boolean isSuccess;

	public JwtResponse(String jwttoken,Boolean isSuccess) {
		this.jwttoken = jwttoken;
		this.isSuccess = isSuccess;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}
}