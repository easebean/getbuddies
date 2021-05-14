package com.getbuddies.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getbuddies.app.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	User findByUserName(String userName);
	List<User> findByNameContainingIgnoreCase(String name);
	
}
