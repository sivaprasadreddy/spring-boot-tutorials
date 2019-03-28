package com.sivalabs.geeksclub.security

import com.sivalabs.geeksclub.entities.User
import com.sivalabs.geeksclub.users.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class SecurityUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user: Optional<User> = userRepository.findByEmail(username)
        if(!user.isPresent) {
            throw UsernameNotFoundException("User email $username not found")
        }
        return SecurityUser(user.get())
    }
}