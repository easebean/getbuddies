package com.getbuddies.app.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getbuddies.app.exception.UserNotFoundException;
import com.getbuddies.app.model.Room;
import com.getbuddies.app.model.User;
import com.getbuddies.app.repo.RoomRepo;
import com.getbuddies.app.repo.UserRepo;

@Service
public class UserService {
	private final UserRepo userRepo;
	private final RoomRepo roomRepo;

	@Autowired
	public UserService(UserRepo userRepo,RoomRepo roomRepo) {
		super();
		this.userRepo = userRepo;
		this.roomRepo = roomRepo;
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
	
	@Transactional
	public void deleteUser(Long id) {
		User user = null;
		try {
			user = findUserById(id);
		} catch (Exception e) {System.out.println(e.getMessage());}
		for(Room room: user.getRooms()) {
			room.getUsers().remove(user);
		}
		user.getRooms().removeAll(user.getRooms());
		userRepo.deleteUserById(id);
	}
}
