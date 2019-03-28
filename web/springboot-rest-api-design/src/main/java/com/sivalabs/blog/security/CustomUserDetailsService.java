/**
 * 
 */
package com.sivalabs.blog.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.blog.entities.User;
import com.sivalabs.blog.repositories.UserRepository;


/**
 * @author Siva
 *
 */
@Component//("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{
	private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired UserRepository userRepository;
	
	@Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);
        User userFromDatabase =  userRepository.findByEmail(email);
        if(userFromDatabase == null){
        	throw new UsernameNotFoundException("User " + email + " was not found");
        }
        return new SecurityUser(userFromDatabase);
    }

}
