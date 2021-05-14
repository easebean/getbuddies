package com.getbuddies.app.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class RoomUsers implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "room_id",nullable = false)
	private Room room;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User member;
	public RoomUsers() {}
	public RoomUsers(Long id, Room room, User member) {
		super();
		this.id = id;
		this.room = room;
		this.member = member;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public User getUser() {
		return member;
	}
	public void setUser(User member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "RoomUsers [id=" + id + ", room=" + room + ", member=" + member + "]";
	}
	
	
}
