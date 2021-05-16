package com.getbuddies.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getbuddies.app.exception.RoomNotFoundException;
import com.getbuddies.app.model.ChatMessage;
import com.getbuddies.app.model.Room;
import com.getbuddies.app.repo.ChatMessageRepo;
import com.getbuddies.app.repo.RoomRepo;

@Service
public class ChatMessageService {
	private final ChatMessageRepo chatRepo;
	private final RoomRepo roomRepo;
	@Autowired
	public ChatMessageService(ChatMessageRepo chatRepo, RoomRepo roomRepo) {
		this.chatRepo = chatRepo;
		this.roomRepo = roomRepo;
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
	public ChatMessage save(ChatMessage chat, Long roomId) {
		Room room = findRoom(roomId);
		chat.setRoom(room);
		return chatRepo.save(chat);
	}
	public List<ChatMessage> list(Long id){
		Room room = findRoom(id);
		return chatRepo.findAllByRoomOrderByCreateDateAsc(room);
	}
}
