package com.getbuddies.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getbuddies.app.exception.RoomNotFoundException;
import com.getbuddies.app.exception.UserNotFoundException;
import com.getbuddies.app.model.Room;
import com.getbuddies.app.model.User;
import com.getbuddies.app.repo.RoomRepo;
import com.getbuddies.app.repo.UserRepo;

@Service
public class RoomService {
	private final RoomRepo roomRepo;
	private final UserRepo userRepo;
	
	@Autowired
	public RoomService(RoomRepo roomRepo, UserRepo userRepo) {
		this.roomRepo = roomRepo;
		this.userRepo = userRepo;
	}
	/*
	 * Create the room and add the owner to the room
	 * Requires room object and user id
	 */
	public Room create(Room room, Long id) {
		User user = findUser(id);
		room.getUsers().add(user);
		return roomRepo.save(room);
	}
	// List all the rooms
	public List<Room> allRooms(){
		return roomRepo.findAll();
	}
	// Update room details
	public Room updateRoom(Room room) {
		return roomRepo.save(room);
	}
	// Delete room
	@Transactional
	public void deleteRoom(Long id) {
		Room room = null;
		try {
			room = findRoomById(id);
		} catch (RoomNotFoundException e) {
			e.printStackTrace();
		}
		room.getUsers().removeAll(room.getUsers());
		room.getChat().removeAll(room.getChat());
		roomRepo.deleteRoomById(id);
	}
	
	public User findUser(Long id) {
		User user = null;
		try {
			user = userRepo.findUserById(id)
					.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	public Room addUserToRoom(Room room, String userName) throws UserNotFoundException {
		User user = null;
		try {
		user = userRepo.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User by username " + userName + " was not found"));
		} catch (Exception e) {System.out.println(e.getMessage());}
		// display user details that has been added 
		System.out.println(user);
		room.getUsers().add(user);
		room = updateRoom(room);
		return room;
	}

	public Room removeUserFromRoom(Room room, Long id) {
		User user = findUser(id);
		// display user details that has been removed 
		System.out.println(user);
		try {
		room.getUsers().removeIf(a -> id.equals(a.getId()));
		user.getRooms().remove(room);
		} catch (Exception e) {System.out.println(e.getMessage());}
		room = updateRoom(room);
		return room;
	}
	
	public List<Room> findRoomByName(String key){
		return roomRepo.findByNameContainingIgnoreCase(key);
	}
	
	public Room findRoomById(Long id) throws RoomNotFoundException {
		Room room = null;
		try {
		room = roomRepo.findRoomById(id)
				.orElseThrow(() -> new RoomNotFoundException("Room by id " + id + " was not found"));
		} catch (Exception e) {System.out.println(e.getMessage());}
		return room;
	}
	
}
