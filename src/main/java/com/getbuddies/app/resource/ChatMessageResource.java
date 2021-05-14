package com.getbuddies.app.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getbuddies.app.model.ChatMessage;
import com.getbuddies.app.service.ChatMessageService;

@RestController
@RequestMapping("/chat")
public class ChatMessageResource {
	private final ChatMessageService chatService;

	public ChatMessageResource(ChatMessageService chatService) {
		this.chatService = chatService;
	}
	
	@GetMapping("/all/{room}")
	public ResponseEntity<List<ChatMessage>> list(@PathVariable("room") Integer id){
		List<ChatMessage> chats = chatService.list(id);
		return new ResponseEntity<List<ChatMessage>>(chats,HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<ChatMessage> add(@RequestBody ChatMessage chat){
		ChatMessage newChat = chatService.save(chat);
		return new ResponseEntity<>(newChat,HttpStatus.CREATED);
	}
	
}
