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
	
	public Room create(Room room, Long id) {
		room = addUser(room, id);
		return roomRepo.save(room);
	}
	
	public List<Room> allRooms(){
		return roomRepo.findAll();
	}
	
	public Room updateRoom(Room room) {
		return roomRepo.save(room);
	}
	@Transactional
	public void deleteRoom(Long id) {
		roomRepo.deleteRoomById(id);
	}
	
	public Room findRoom(Long id) {
		Room room = null;
		try {
			room = roomRepo.findRoomById(id)
					.orElseThrow(() -> new RoomNotFoundException("Room by id " + id + " was not found"));
		} catch (RoomNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return room;
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
	
	public Room addUser(Room room, Long id) {
		User user = findUser(id);
		System.out.println(user);
		room.getUsers().add(user);
		return room;
	}

	public Room addUserToRoom(Long roomId, Long id) {
		Room room = findRoom(roomId);
		room = addUser(room, id);
		room = updateRoom(room);
		return room;
	}

	public Room removeUserFromRoom(Long roomId, Long id) {
		Room room = findRoom(roomId);
		User user = findUser(id);
		room.getUsers().remove(user);
		room = updateRoom(room);
		return room;
	}
}
