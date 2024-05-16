package com.aptitude.service.implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptitude.entity.User;
import com.aptitude.entity.UserRole;
import com.aptitude.helper.UserFoundException;
import com.aptitude.repository.RoleRepository;
import com.aptitude.repository.UserRepository;
import com.aptitude.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository  userRepo;
	
	@Autowired
	private RoleRepository  roleRepo;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User existingUser = userRepo.findByUsername(user.getUsername());
		User newUser;
		if(existingUser!=null) {
			
			System.out.println("User already present");
			throw new UserFoundException("User already present");
		}
		else {
			
			for(UserRole userRole: userRoles) {
				roleRepo.save(userRole.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
		    newUser = userRepo.save(user);
		}
		
		return newUser;
	}
	
	
	
	@Override
    public User getUser(String username) {
        return this.userRepo.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepo.deleteById(userId);
    }

}
