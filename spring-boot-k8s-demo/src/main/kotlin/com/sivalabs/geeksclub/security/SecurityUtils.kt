package com.sivalabs.geeksclub.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

object SecurityUtils {

    fun loggedinUser() : Optional<UserDetails> {
        val authentication = SecurityContextHolder.getContext().authentication
        return if (authentication != null && authentication.principal is UserDetails) {
            Optional.of(authentication.principal as UserDetails)
        } else Optional.empty()
    }
}