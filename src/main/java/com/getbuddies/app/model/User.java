package com.getbuddies.app.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column
	private String city;
	@OneToMany(mappedBy = "member")
	private Set<RoomUsers> roomUsers;
	public User() {}
	public User(String name, String userName, String password, String email, String phoneNumber, String city,
			Set<RoomUsers> roomUsers) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.roomUsers = roomUsers;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Set<RoomUsers> getRoomUsers() {
		return roomUsers;
	}
	public void setRoomUsers(Set<RoomUsers> roomUsers) {
		this.roomUsers = roomUsers;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", city=" + city + ", roomUsers=" + roomUsers + "]";
	}	
	
}
