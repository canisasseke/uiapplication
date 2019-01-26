package com.zopenlab.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;


@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	@NotNull
	private String password;
	@NotNull
	private boolean active;
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<Role> roles;
	
	public User() {
		super();
	}
	public User(String username, String password, boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", active=" + active + ", roles=" + roles
				+ "]";
	}
	
	
}
