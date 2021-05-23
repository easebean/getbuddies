package com.getbuddies.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getbuddies.app.exception.UserNotFoundException;
import com.getbuddies.app.model.User;
import com.getbuddies.app.repo.UserRepo;

@Service
public class UserService {
	private final UserRepo userRepo;

	@Autowired
	public UserService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public User addUser(User user) {
		return userRepo.save(user);
	}

	public User updateUser(User user) {
		return userRepo.save(user);
	}

	public List<User> allUsers() {
		return userRepo.findAll();
	}

	public User findUserByUserName(String userName, String password) throws UserNotFoundException {
		User user = userRepo.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User with username : " + userName + " not found"));
		if (user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	public List<User> findUserByName(String key) {
		return userRepo.findByNameContainingIgnoreCase(key);
	}
}
