package com.aptitude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aptitude.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);

}
