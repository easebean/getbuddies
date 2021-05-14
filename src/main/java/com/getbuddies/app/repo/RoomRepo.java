package com.getbuddies.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getbuddies.app.model.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
	void deleteRoomById(Long id);
}
