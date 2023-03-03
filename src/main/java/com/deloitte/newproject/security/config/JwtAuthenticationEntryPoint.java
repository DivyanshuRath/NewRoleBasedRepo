package com.deloitte.newproject.security.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	@Value("${auth.cookie.key}")
	private String authKey;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException {

		Cookie cookie = WebUtils.getCookie(request, authKey);
		if(cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}

