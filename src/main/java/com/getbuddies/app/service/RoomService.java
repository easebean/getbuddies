package com.getbuddies.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getbuddies.app.model.Room;
import com.getbuddies.app.repo.RoomRepo;

@Service
public class RoomService {
	private final RoomRepo roomRepo;

	@Autowired
	public RoomService(RoomRepo roomRepo) {
		this.roomRepo = roomRepo;
	}
	
	public Room create(Room room) {
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
}
