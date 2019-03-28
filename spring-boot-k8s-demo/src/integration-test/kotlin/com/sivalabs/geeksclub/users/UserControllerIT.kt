package com.sivalabs.geeksclub.users

import com.sivalabs.geeksclub.any
import com.sivalabs.geeksclub.entities.Link
import com.sivalabs.geeksclub.entities.User
import com.sivalabs.geeksclub.techfeed.TechFeedService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.util.LinkedMultiValueMap

import java.util.ArrayList
import java.util.Optional

import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var techFeedService: TechFeedService

    @MockBean
    lateinit var userService: UserService

    //private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

    @Test
    @Throws(Exception::class)
    fun shouldRegisterUserSuccessfully() {
        val user = User()
        given(this.userService.createUser(any())).willReturn(user)

        val params = LinkedMultiValueMap<String, String>()
        params.add("email", "me@gmail.com")
        params.add("password", "secret")
        params.add("name", "Myname")

        this.mvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params)
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/registration_status"))
                .andReturn()
        Mockito.verify(userService).createUser(any())
    }

    @Test
    @Throws(Exception::class)
    fun showUserProfile() {
        val userId = 1L
        val user = Optional.of(User())
        val links = ArrayList<Link>()
        given(this.userService.findUserById(userId)).willReturn(user)
        given(this.techFeedService.findLinksByCreatedUserId(userId)).willReturn(links)

        this.mvc.perform(get("/users/{userId}", userId)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk)
                .andExpect(view().name("userprofile"))
                .andExpect(model().attributeExists("userProfile"))
        Mockito.verify(userService).findUserById(userId)
        Mockito.verify(techFeedService).findLinksByCreatedUserId(userId)
    }
}