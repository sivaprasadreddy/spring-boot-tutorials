package com.sivalabs.geeksclub.techfeed

import com.sivalabs.geeksclub.entities.Link
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long> {

    fun findByTitleContainingIgnoreCase(q: String, sort: Sort): List<Link>

    fun findByCreatedById(userId: Long): List<Link>

    fun findByCategoryIdAndTitleContainingIgnoreCase(catId: Long, query: String): List<Link>
}