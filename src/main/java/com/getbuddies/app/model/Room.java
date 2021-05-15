package com.getbuddies.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String name;
	@Column
	private char category;
	@Column(name = "created_by")
	private String createdBy;
	@Column
	private Date created;
	@Column(name = "end_time")
	private Date endTime;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "room_user",
            joinColumns = {
                    @JoinColumn(name = "room_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	private Set<User> users = new HashSet<>(); 
	@OneToMany(mappedBy = "room")
	private Set<ChatMessage> chat;
	public Room() {}
	public Room(Long id, String name, char category, String createdBy, Date created, Date endTime, Set<User> users,
			Set<ChatMessage> chat) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.createdBy = createdBy;
		this.created = created;
		this.endTime = endTime;
		this.users = users;
		this.chat = chat;
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
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<ChatMessage> getChat() {
		return chat;
	}
	public void setChat(Set<ChatMessage> chat) {
		this.chat = chat;
	}	
}
