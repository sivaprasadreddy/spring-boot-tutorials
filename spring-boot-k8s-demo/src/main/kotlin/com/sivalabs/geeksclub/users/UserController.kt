package com.sivalabs.geeksclub.users

import com.sivalabs.geeksclub.entities.User
import com.sivalabs.geeksclub.techfeed.TechFeedService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class UserController(val techFeedService: TechFeedService,
                     val userService: UserService) {

    @GetMapping("/registration")
    fun showRegistrationForm(model: Model) : String {
        model["user"]= User()
        return "registration"
    }

    @PostMapping("/registration")
    fun register(@ModelAttribute("user") @Validated user: User,
                 errors: BindingResult,
                 redirectAttributes: RedirectAttributes) : String {
        if(errors.hasErrors()) {
            return "registration"
        }
        val userWithEmail = userService.findUserByEmail(user.email)
        if(userWithEmail.isPresent){
            errors.rejectValue("email","user.email.exist",
                    "User already registered with email ${user.email}")
            return "registration"
        }
        userService.createUser(user)
        redirectAttributes.addFlashAttribute("message", "Registration is successful")
        return "redirect:/registration_status"
    }

    @GetMapping("/users/{userId}")
    fun showUserProfile(@PathVariable("userId") userId: Long, model: Model) : String {
        val user = userService.findUserById(userId).get()
        val links = techFeedService.findLinksByCreatedUserId(userId)
        val userProfile = UserProfile(user.id ?: 0, user.name, user.email, user.website, user.bio, links)
        model["userProfile"]= userProfile
        return "userprofile"
    }
}