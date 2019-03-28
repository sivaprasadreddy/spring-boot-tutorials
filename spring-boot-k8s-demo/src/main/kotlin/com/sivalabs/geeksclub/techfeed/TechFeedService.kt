package com.sivalabs.geeksclub.techfeed


import com.sivalabs.geeksclub.entities.Category
import com.sivalabs.geeksclub.entities.Link
import com.sivalabs.geeksclub.entities.Tag
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.*

@Service
@Transactional
class TechFeedService(val categoryRepository: CategoryRepository,
                      val linkRepository: LinkRepository,
                      val tagRepository: TagRepository) {

    fun findLinksByTag(tag: String): List<Link> {
        val tagOptional = tagRepository.findByName(tag)
        return if (tagOptional.isPresent) {
            ArrayList(tagOptional.get().links)
        } else {
            ArrayList()
        }
    }

    fun searchLinks(query: String): List<Link> {
        val sort = Sort.by(Sort.Direction.DESC, "createdOn")
        return linkRepository.findByTitleContainingIgnoreCase(query, sort)
    }

    fun saveLink(link: NewLinkDTO) : Link {
        val newlink = Link()
        newlink.title = link.title
        newlink.url = link.url
        val tagsList = mutableListOf<Tag>()
        link.tags.split(",").forEach { tagName ->
            if (tagName.trim() != "") {
                val tag = createTagIfNotExist(tagName.trim())
                tagsList.add(tag)
            }
        }
        newlink.category = categoryRepository.findById(link.category.id?:0).get()
        newlink.tags = tagsList
        newlink.createdBy = link.createdBy
        return linkRepository.save(newlink)
    }

    private fun createTagIfNotExist(tagName : String): Tag {
        val tagOptional = tagRepository.findByName(tagName)
        if (tagOptional.isPresent) {
            return tagOptional.get()
        }

        val tag = Tag()
        tag.name = tagName
        return tagRepository.save(tag)
    }

    fun findLinksByCreatedUserId(userId: Long): List<Link> {
        return linkRepository.findByCreatedById(userId)
    }

    fun findAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    fun filterCategoryLinks(categoryName: String, tag: String, query: String): List<Link> {
        val category = categoryRepository.findByName(categoryName)
        if(!category.isPresent) {
            return listOf()
        }
        val catId = category.get().id ?: 0

        //I strongly believe this can be done better
        val links = linkRepository.findByCategoryIdAndTitleContainingIgnoreCase(catId, query)
        if(tag == "") {
            return links
        }
        val filteredLinks = mutableListOf<Link>()
        links.forEach{l ->
            l.tags.forEach { t ->
                if(t.name == tag) {
                    filteredLinks.add(l)
                }
            }
        }

        return filteredLinks
    }

    fun findCategoryByName(name: String): Optional<Category> {
        return categoryRepository.findByName(name)
    }
}