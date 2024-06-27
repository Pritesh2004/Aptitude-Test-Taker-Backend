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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserFoundException("User already present");
        }

        userRoles.forEach(userRole -> roleRepo.save(userRole.getRole()));
        user.getUserRoles().addAll(userRoles);
        return userRepo.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
