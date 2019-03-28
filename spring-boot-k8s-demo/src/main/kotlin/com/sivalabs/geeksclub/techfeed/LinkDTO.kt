package com.sivalabs.geeksclub.techfeed

import com.sivalabs.geeksclub.entities.Link
import java.time.LocalDateTime

data class LinkDTO (val id : Long?,
               val title : String,
               val url : String,
               val createdOn : LocalDateTime,
               val createdUserId: Long?,
               val createdUserName: String,
               val createdUserEmail: String,
               val tags: List<String>
               )
{
    constructor(link: Link) : this(
            link.id,
            link.title,
            link.url,
            link.createdOn,
            link.createdBy.id,
            link.createdBy.name,
            link.createdBy.email,
            link.tags.map { it.name }
            )
}