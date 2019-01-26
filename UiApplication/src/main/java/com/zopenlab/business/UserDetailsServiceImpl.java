package com.zopenlab.business;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zopenlab.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IAccountHandlerBusiness accountHandlerBusiness;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user =accountHandlerBusiness.findUserById(username);
		if(user==null) throw new UsernameNotFoundException("bad credentials");
		if(!user.isActive()) throw new LockedException("account locked");
		Collection<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
		user.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	
	}

}
