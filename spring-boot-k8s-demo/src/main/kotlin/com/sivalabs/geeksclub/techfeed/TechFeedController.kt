package com.sivalabs.geeksclub.techfeed

import com.sivalabs.geeksclub.entities.Category
import com.sivalabs.geeksclub.entities.Link
import com.sivalabs.geeksclub.entities.Tag
import com.sivalabs.geeksclub.security.SecurityUtils
import com.sivalabs.geeksclub.users.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
class TechFeedController(val techFeedService: TechFeedService, val userService: UserService) {

    @GetMapping("/")
    fun index(model: Model): String {
        model["categories"] = techFeedService.findAllCategories()
        return "index"
    }

    @GetMapping("/category/{name}")
    fun category(@PathVariable("name") name: String,
                 @RequestParam(name = "query", defaultValue = "") query: String,
                 @RequestParam(name = "tag", defaultValue = "") tag: String,
                 model: Model): String {
        val category = techFeedService.findCategoryByName(name)
        if(category.isPresent){
            model["category"] = category.get()
            val categoryLinks = techFeedService.filterCategoryLinks(name, tag, query)
            model["links"] = categoryLinks
            model["categoryTags"] = categoryTags(categoryLinks)
        } else {
            model["category"] = Category()
            model["links"] = listOf<Link>()
        }
        model["searchTag"] = tag
        model["query"] = query
        return "category"
    }

    private fun categoryTags(categoryLinks: List<Link>) : Set<Tag> {
        var tags = mutableSetOf<Tag>()
        categoryLinks.forEach { tags.addAll(it.tags) }
        return tags
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/newlink")
    fun newLinkForm(model: Model): String {
        model["link"] = NewLinkDTO()
        model["categories"] = techFeedService.findAllCategories()
        return "newlink"
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/links")
    fun createLink(@ModelAttribute("link") @Validated link: NewLinkDTO,
                            errors: BindingResult): String {
        if (errors.hasErrors()) {
            return "newlink"
        }
        val loginUser = SecurityUtils.loggedinUser()
        val user = userService.findUserByEmail(loginUser.get().username)
        link.createdBy = user.get()
        techFeedService.saveLink(link)
        return "redirect:/"
    }

}