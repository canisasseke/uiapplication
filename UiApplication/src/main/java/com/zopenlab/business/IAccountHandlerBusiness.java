package com.zopenlab.business;

import java.util.List;

import com.zopenlab.models.Role;
import com.zopenlab.models.User;

public interface IAccountHandlerBusiness {

	
	//user
	public User saveUser(User user);
	public User updateUser(User user,String userid);
	public List<User> getAllUsers();
	public User findUserById(String userid);
	public void deleteUser(String userid);
	//roles
	public Role saveRole(Role role);
	public Role updateRole(Role role, String roleid);
	public List<Role> getAllRoles();
	public Role findRoleById(String roleid);
	public void deleteRole(String roleid);
	
	//
	public void addRoleToUser(String userid,String roleid);
	public void addRolesToUser(String userid,List<Role> roles);
	
}
