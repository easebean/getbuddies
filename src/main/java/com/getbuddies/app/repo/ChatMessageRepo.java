package com.getbuddies.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getbuddies.app.model.ChatMessage;
import com.getbuddies.app.model.Room;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long>{
	List<ChatMessage> findAllByRoomOrderByCreateDateAsc(Room room);
}
