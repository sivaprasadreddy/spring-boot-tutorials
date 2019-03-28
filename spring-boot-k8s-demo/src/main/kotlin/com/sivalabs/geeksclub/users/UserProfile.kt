package com.sivalabs.geeksclub.users

import com.sivalabs.geeksclub.entities.Link

data class UserProfile (
    val id: Long,
    val name: String,
    val email: String,
    val website: String = "",
    val bio: String = "",
    val postedLinks: List<Link> = listOf()
)