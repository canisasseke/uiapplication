package com.zopenlab.security;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zopenlab.models.User;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	

	public RequestBodyReaderAuthenticationFilter() {
		super();
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		User user=null;
		try {
			 user = objectMapper.readValue(request.getInputStream(), User.class);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		setDetails(request, token);
		return this.getAuthenticationManager().authenticate(token);
	}



	
	
}
