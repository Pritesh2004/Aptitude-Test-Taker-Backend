package com.aptitude.service;

import java.util.Set;

import com.aptitude.entity.User;
import com.aptitude.entity.UserRole;

public interface UserService {

	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user by username
    public User getUser(String username);

    //delete user by id
    public void deleteUser(Long userId);
}
