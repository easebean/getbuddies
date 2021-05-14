package com.getbuddies.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getbuddies.app.model.ChatMessage;
import com.getbuddies.app.repo.ChatMessageRepo;

@Service
public class ChatMessageService {
	private ChatMessageRepo chatRepo;
	
	@Autowired
	public ChatMessageService(ChatMessageRepo chatRepo) {
		super();
		this.chatRepo = chatRepo;
	}
	
	public ChatMessage save(ChatMessage chat) {
		return chatRepo.save(chat);
	}
	public List<ChatMessage> list(Integer id){
		return chatRepo.findAllByRoomOrderByCreateDateAsc(id);
	}
}
