package com.getbuddies.app.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getbuddies.app.model.Room;
import com.getbuddies.app.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomResource {
	private final RoomService roomService;

	public RoomResource(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Room>> list(){
		List<Room> rooms = roomService.allRooms(); 
		return new ResponseEntity<List<Room>>(rooms,HttpStatus.OK);
	}
	
	@PostMapping("/create/{id}")
	public ResponseEntity<Room> addRoom(@RequestBody Room room,@PathVariable("id") Long id){
		Room newRoom = roomService.create(room,id);
		return new ResponseEntity<Room>(newRoom,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Room> updateRoom(@RequestBody Room room){
		Room updateRoom = roomService.updateRoom(room);
		return new ResponseEntity<Room>(updateRoom,HttpStatus.OK);
	}
	
	@PutMapping("/{roomId}/add/{id}")
	public ResponseEntity<Room> addUser(@PathVariable("roomId") Long roomId,@PathVariable("id") Long id){
		Room updateRoom = roomService.addUserToRoom(roomId,id);
		return new ResponseEntity<Room>(updateRoom,HttpStatus.OK);
	}
	
	@PutMapping("/{roomId}/remove/{id}")
	public ResponseEntity<Room> removeUser(@PathVariable("roomId") Long roomId,@PathVariable("id") Long id){
		Room updateRoom = roomService.removeUserFromRoom(roomId,id);
		return new ResponseEntity<Room>(updateRoom,HttpStatus.OK);
	}
	
	@GetMapping("/find/{name}")
	public ResponseEntity<List<Room>> getRoomsByName(@PathVariable("name") String key){
		List<Room> rooms = roomService.findRoomByName(key);
		return new ResponseEntity<List<Room>>(rooms,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id){
		roomService.deleteRoom(id);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
