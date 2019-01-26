package com.zopenlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zopenlab.business.IAccountHandlerBusiness;
import com.zopenlab.models.Role;
import com.zopenlab.models.User;

@SpringBootApplication
public class UiApplication implements CommandLineRunner {

	@Autowired
	IAccountHandlerBusiness accountHandlerBusiness;
	
	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//save users
		accountHandlerBusiness.saveUser(new User("user", encoder.encode("user"), true));
		accountHandlerBusiness.saveUser(new User("admin", encoder.encode("user"), false));
		
		//save roles
		accountHandlerBusiness.saveRole(new Role("USER", "user"));
		accountHandlerBusiness.saveRole(new Role("ADMIN", "administrator"));
		
		//get users and roles
		System.out.println(accountHandlerBusiness.getAllUsers());
		System.out.println(accountHandlerBusiness.getAllRoles());
		
		//add roles to user
		accountHandlerBusiness.addRoleToUser("user", "USER");
		accountHandlerBusiness.addRoleToUser("admin", "USER");
		accountHandlerBusiness.addRoleToUser("admin", "ADMIN");
		//users with roles
		
		System.out.println(accountHandlerBusiness.getAllUsers());
	}

}

