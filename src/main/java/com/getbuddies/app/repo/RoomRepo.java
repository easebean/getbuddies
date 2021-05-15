package com.getbuddies.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getbuddies.app.model.Room;
import com.getbuddies.app.model.User;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
	void deleteRoomById(Long id);

	Optional<Room> findRoomById(Long id);
}
