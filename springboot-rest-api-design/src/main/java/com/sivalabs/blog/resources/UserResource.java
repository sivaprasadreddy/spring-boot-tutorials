/**
 * 
 */
package com.sivalabs.blog.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Siva
 *
 */
public class UserResource 
{

	private String email;
	private String password;
	private List<String> roles = new ArrayList<String>();
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public List<String> getRoles()
	{
		return roles;
	}
	public void setRoles(List<String> roles)
	{
		this.roles = roles;
	}
	
}
