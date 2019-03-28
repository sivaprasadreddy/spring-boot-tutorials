/**
 * 
 */
package com.sivalabs.blog.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Siva
 *
 */
public class CommentDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String email;
	private String content;
	private Date createdOn = new Date();
	private Date updatedOn;	
	private Integer postId;

	public CommentDTO()
	{
	}
	/*
	public CommentDTO(Comment comment)
	{
		this.id = comment.getId();
		this.name = comment.getName();
		this.email = comment.getEmail();
		this.content = comment.getContent();
		this.createdOn = comment.getCreatedOn();
		this.updatedOn = comment.getUpdatedOn();
		this.postId = comment.getPost().getId();
	}*/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getPostId()
	{
		return postId;
	}

	public void setPostId(Integer postId)
	{
		this.postId = postId;
	}

}
