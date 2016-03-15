/**
 * 
 */
package com.sivalabs.demo.entities;

import java.sql.Timestamp;

/**
 * @author Siva
 *
 */
public class Comment
{
	private Integer id;
	private Post post;
	private String name;
	private String email;
	private String content;
	private Timestamp createdOn;
	
	public Comment()
	{
	}
	
	public Comment(Integer id, Integer postId, String name, String email, String content, Timestamp createdOn)
	{
		super();
		this.id = id;
		this.post = new Post(postId);
		this.name = name;
		this.email = email;
		this.content = content;
		this.createdOn = createdOn;
	}

	@Override
	public String toString()
	{
		return "Comment [id=" + id + ", postId=" + post.getId() + ", name=" + name + ", email=" + email + ", content=" + content
		        + ", createdOn=" + createdOn + "]";
	}

	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Post getPost()
	{
		return post;
	}
	public void setPost(Post post)
	{
		this.post = post;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Timestamp getCreatedOn()
	{
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn)
	{
		this.createdOn = createdOn;
	}
	
}
