package com.deloitte.newproject.security.model;

import java.io.Serializable;
import javax.servlet.http.HttpServletResponse;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final HttpServletResponse httpHeaders;

	public JwtResponse(String jwttoken,  HttpServletResponse httpHeaders) {
		this.jwttoken = jwttoken;
		this.httpHeaders = httpHeaders;
	}

	public String getToken() {
		return this.jwttoken;
	}
}