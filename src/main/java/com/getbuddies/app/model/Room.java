package com.getbuddies.app.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Room implements Serializable{

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
	private char category;
	@Column(name = "created_by")
	private String createdBy;
	@Column
	private Date created;
	@Column(name = "end_time")
	private Date endTime;
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private Set<RoomUsers> roomUsers;
	@OneToOne(mappedBy = "room")
	private ChatMessage chat;
	public Room() {}
	public Room(Long id, String name, char category, String createdBy, Date created, Date endTime) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.createdBy = createdBy;
		this.created = created;
		this.endTime = endTime;
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
	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", category=" + category + ", createdBy=" + createdBy
				+ ", created=" + created + ", endTime=" + endTime + "]";
	}
	
}
