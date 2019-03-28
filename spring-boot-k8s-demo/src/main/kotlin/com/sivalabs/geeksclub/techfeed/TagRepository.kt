package com.sivalabs.geeksclub.techfeed

import com.sivalabs.geeksclub.entities.Tag
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TagRepository : JpaRepository<Tag, Long> {
    fun findByName(tag: String): Optional<Tag>
}