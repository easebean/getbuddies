package com.getbuddies.app.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getbuddies.app.exception.UserNotFoundException;
import com.getbuddies.app.model.User;
import com.getbuddies.app.service.UserService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserResource {
	private final UserService userService;
	
	public UserResource(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.allUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/find/{name}")
	public ResponseEntity<List<User>> getUsersByName(@PathVariable("name") String name){
		List<User> users = userService.findUserByName(name);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/login/{user}/{pass}")
	public ResponseEntity<User> authUser(@PathVariable("user") String userName, @PathVariable("pass") String password){
		User user = null;
		try {
			user = userService.findUserByUserName(userName,password);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User updateUser = userService.updateUser(user);
		return new ResponseEntity<User>(updateUser,HttpStatus.OK);
	}
}
