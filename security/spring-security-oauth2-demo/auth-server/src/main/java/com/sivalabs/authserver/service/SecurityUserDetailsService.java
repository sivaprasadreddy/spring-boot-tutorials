package com.sivalabs.authserver.service;

import com.sivalabs.authserver.entity.UserAccount;
import com.sivalabs.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> optionalUserAccount = this.userRepository.findByEmail(username);
        if(!optionalUserAccount.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        return optionalUserAccount.get();
    }
}
