package com.getbuddies.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chats")
public class ChatMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String text;
    private String author;
    private Date createDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;
    
	public ChatMessage() {
	}
	public ChatMessage(String text, String author, Date createDate) {
		this.text = text;
		this.author = author;
		this.createDate = createDate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", text=" + text + ", author=" + author + ", createDate=" + createDate + "]";
	}
    
}
