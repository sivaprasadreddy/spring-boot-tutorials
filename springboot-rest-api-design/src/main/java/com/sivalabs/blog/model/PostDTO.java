package com.sivalabs.blog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Siva
 *
 */
public class PostDTO
{
	private Integer id;
	private String title;
	private String content;
	private UserDTO createdBy;
	private Date createdOn = new Date();
	private Date updatedOn;
	private List<CommentDTO> comments = new ArrayList<>();

	public PostDTO()
	{
	}
	/*
	public PostDTO(Post post)
	{
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.createdBy = post.getCreatedBy();
		this.createdOn = post.getCreatedOn();
		this.updatedOn = post.getUpdatedOn();
		this.comments = new ArrayList<>();
		for (Comment comment : post.getComments())
		{
			this.comments.add(new CommentDTO(comment));
		}
	}
	*/
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public UserDTO getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(UserDTO createdBy)
	{
		this.createdBy = createdBy;
	}
	public Date getCreatedOn()
	{
		return createdOn;
	}
	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn()
	{
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn)
	{
		this.updatedOn = updatedOn;
	}
	public List<CommentDTO> getComments()
	{
		return comments;
	}
	public void setComments(List<CommentDTO> comments)
	{
		this.comments = comments;
	}
	
}
