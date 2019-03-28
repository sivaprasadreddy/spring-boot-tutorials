package com.sivalabs.blog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Siva
 * 
 */
public class UserDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String password;
	private String name;

    private Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO()
	{
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RoleDTO> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles)
	{
		this.roles = roles;
	}

}
