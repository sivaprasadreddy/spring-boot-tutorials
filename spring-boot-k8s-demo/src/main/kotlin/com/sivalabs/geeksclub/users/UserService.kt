package com.sivalabs.geeksclub.users

import com.sivalabs.geeksclub.entities.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.Optional

@Service
@Transactional
class UserService(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {

    fun findUserByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    fun createUser(user: User): User {
        user.password=passwordEncoder.encode(user.password)
        user.role = ("ROLE_USER")
        return userRepository.save(user)
    }

    fun findUserById(userId: Long): Optional<User> {
        return userRepository.findById(userId)
    }
}