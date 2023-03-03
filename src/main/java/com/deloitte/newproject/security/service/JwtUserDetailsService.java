package com.deloitte.newproject.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.password}")
	private String jwtPassword;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		CharSequence password = jwtPassword;

		if (jwtSecret.equals(username))
			return new User(jwtSecret, bCryptPasswordEncoder.encode(password),
					new ArrayList<>());
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}