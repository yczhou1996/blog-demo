package com.zyc.domain;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@Column(length=50)
	private String title;
	@Column(length=5000)
	private String content;
	@Column
	private Long user_id;
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	@Column
	private Integer readSize=0; 
	@Column
	private Integer commentSize=0;  
	@Column
	private Integer likeSize=0;
	@Column
	private Date createTime;
	@Column
	private String time;
	@Column
	private String photo;

	public Integer getReadSize() {
		return readSize;
	}

	public void setReadSize(Integer readSize) {
		this.readSize = readSize;
	}

	public Integer getCommentSize() {
		return commentSize;
	}

	public void setCommentSize(Integer commentSize) {
		this.commentSize = commentSize;
	}

	public Integer getLikeSize() {
		return likeSize;
	}

	public void setLikeSize(Integer likeSize) {
		this.likeSize = likeSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
