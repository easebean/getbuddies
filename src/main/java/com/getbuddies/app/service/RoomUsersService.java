package com.getbuddies.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getbuddies.app.model.RoomUsers;
import com.getbuddies.app.repo.RoomUsersRepo;

@Service
public class RoomUsersService {
	private final RoomUsersRepo roomUsersRepo;

	@Autowired
	public RoomUsersService(RoomUsersRepo roomUsersRepo) {
		this.roomUsersRepo = roomUsersRepo;
	}
	
	public RoomUsers add(RoomUsers roomUsers) {
		return roomUsersRepo.save(roomUsers);
	}
	
}
