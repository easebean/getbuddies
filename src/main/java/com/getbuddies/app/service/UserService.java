package com.getbuddies.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getbuddies.app.exception.UserNotFoundException;
import com.getbuddies.app.model.Room;
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

	public Set<Room> allRooms(Long id) {
		User user = null;
		try {
			user = userRepo.findUserById(id)
					.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
			return user.getRooms();
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<User> allUsers() {
		return userRepo.findAll();
	}

	public User findUserByUserName(String userName, String password) throws UserNotFoundException {
		User user = userRepo.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User with username : " + userName + " was not found"));
		if (user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	public User findUserByUserName(String userName) throws UserNotFoundException {
		User user = null;
		try{
			user = userRepo.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User with username : " + userName + " was not found"));
		}catch (Exception e) {System.out.println(e.getMessage());}
		return user;
	}

	public User findUserById(Long id) {
		User user = null;
		try {
			user = userRepo.findUserById(id)
					.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	public List<User> findUserByName(String key) {
		return userRepo.findByNameContainingIgnoreCase(key);
	}
}
