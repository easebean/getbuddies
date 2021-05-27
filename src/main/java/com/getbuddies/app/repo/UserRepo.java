package com.getbuddies.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getbuddies.app.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	void deleteUserById(Long id);
	Optional<User> findUserById(Long id);
	Optional<User> findByUserName(String userName);
	List<User> findByNameContainingIgnoreCase(String name);
	
}
