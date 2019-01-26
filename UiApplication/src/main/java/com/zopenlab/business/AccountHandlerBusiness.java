package com.zopenlab.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zopenlab.dao.IRoleDao;
import com.zopenlab.dao.IUserDao;
import com.zopenlab.exceptions.RoleNotFoundException;
import com.zopenlab.models.Role;
import com.zopenlab.models.User;

@Service
@Transactional
public class AccountHandlerBusiness implements IAccountHandlerBusiness {

	@Autowired
	IUserDao userDao;
	@Autowired
	IRoleDao roleDao;
	
	
	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User updateUser(User user, String userid) {
		User u=findUserById(userid);
		user.setUsername(u.getUsername());
		return userDao.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User findUserById(String userid) {
		Optional<User> opt=userDao.findById(userid);
		if(!opt.isPresent()) throw new UsernameNotFoundException("user not found");
		return opt.get();
	}

	@Override
	public void deleteUser(String userid) {
		User user =findUserById(userid);
		userDao.delete(user);
		
	}

	@Override
	public Role saveRole(Role role) {
		return roleDao.save(role);
	}

	@Override
	public Role updateRole(Role role, String roleid) {
		Role r =findRoleById(roleid);
		role.setRoleName(r.getRoleName());
		return roleDao.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.findAll();
	}

	@Override
	public Role findRoleById(String roleid) {
		Optional<Role> opt=roleDao.findById(roleid);
		if(!opt.isPresent()) throw new RoleNotFoundException("role not found");
		return opt.get();
	}

	@Override
	public void deleteRole(String roleid) {
		Role role=findRoleById(roleid);
		roleDao.delete(role);
		
	}

	@Override
	public void addRoleToUser(String userid, String roleid) {
		User user = findUserById(userid);
		Role role = findRoleById(roleid);
		user.getRoles().add(role);
		
	}

	@Override
	public void addRolesToUser(String userid, List<Role> roles) {
		User user = findUserById(userid);
		user.getRoles().clear();
		user.getRoles().addAll(roles);
		
	}

}
