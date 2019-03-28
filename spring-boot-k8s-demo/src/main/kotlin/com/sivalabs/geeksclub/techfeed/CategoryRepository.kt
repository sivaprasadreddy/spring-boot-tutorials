package com.sivalabs.geeksclub.techfeed

import com.sivalabs.geeksclub.entities.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category, Long> {

    fun findByName(name: String): Optional<Category>
}