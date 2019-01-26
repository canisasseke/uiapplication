package com.zopenlab.rest;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RessourceTest {
	
	@GetMapping("/resource")
	public Map<String, Object> home() {
		 Map<String,Object> model = new HashMap<>();
		    model.put("id", UUID.randomUUID().toString());
		    model.put("content", "Hello World");
		    return model;
	}
	@GetMapping("/logedUser")
	public Map<String, Object> getLogedUser(){
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		List<String> roles =new ArrayList<String>();
		for(GrantedAuthority ga:SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String, Object> params=new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;
		
	}
	@GetMapping("/getLogedUser")
	public Map<String, Object> getLogedUser(HttpServletRequest request){
		HttpSession httpSession = request.getSession();
		SecurityContext securityContext =(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username= securityContext.getAuthentication().getName();
		List<String> roles =new ArrayList<String>();
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String, Object> params=new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;
		
	}
	@GetMapping("/user")
	public Principal user(Principal user) {
	    return user;
	  }

}
