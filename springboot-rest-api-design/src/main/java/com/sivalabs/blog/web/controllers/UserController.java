/**
 * 
 */
package com.sivalabs.blog.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sivalabs.blog.entities.Role;
import com.sivalabs.blog.model.ServiceResponse;
import com.sivalabs.blog.resources.UserResource;
import com.sivalabs.blog.security.SecurityUser;
import com.sivalabs.blog.security.Token;
import com.sivalabs.blog.security.TokenProvider;

/**
 * @author Siva
 *
 */
@RestController
@RequestMapping(value="/api")
public class UserController 
{
	@Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Token authorize(@RequestParam String username, @RequestParam String password) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = this.userDetailsService.loadUserByUsername(username);
        return tokenProvider.createToken(details);
    }
    
	@RequestMapping(value="/loginUser", method=RequestMethod.GET)
	public ServiceResponse<UserResource> getAuthenticatedUser() 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!= null)
		{
			Object userDetails = authentication.getPrincipal();
			if(userDetails != null && userDetails instanceof SecurityUser)
			{
				SecurityUser secUser = (SecurityUser) userDetails;
				String username = secUser.getUsername();
				List<String> roles = new ArrayList<>();
				Set<Role> rolesSet = secUser.getDomainUser().getRoles();
				for (Role role : rolesSet)
				{
					roles.add(role.getName());
				}
				UserResource authenticatedUser = new UserResource();
				authenticatedUser.setEmail(username);
				authenticatedUser.setRoles(roles);
				return new ServiceResponse<>(authenticatedUser); 
			}
		}
		return new ServiceResponse<>(HttpStatus.UNAUTHORIZED);
		
	}
}
