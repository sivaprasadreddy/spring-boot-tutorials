package com.sivalabs.geeksclub.techfeed

import com.sivalabs.geeksclub.any
import com.sivalabs.geeksclub.entities.Category
import com.sivalabs.geeksclub.entities.Link
import com.sivalabs.geeksclub.entities.User
import org.junit.Before
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

import org.hamcrest.Matchers.hasSize
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class TechFeedControllerIT {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var techFeedService: TechFeedService

    lateinit var loginUser: User
    lateinit var link1: Link
    lateinit var link2: Link

    lateinit var category1: Category
    lateinit var category2: Category

    lateinit var categories: List<Category>
    lateinit var links: List<Link>

    @Before
    fun init() {
        loginUser = User()
        loginUser.id = 1L

        link1 = Link()
        link1.id = 1L
        link1.title = "Title1"
        link1.createdBy = loginUser

        link2 = Link()
        link2.id = 2L
        link2.title = "Title2"
        link2.createdBy = loginUser

        links = Arrays.asList(link1, link2)

        category1 = Category()
        category1.id=1L
        category1.name="cat1"
        category1.label="Cat1"

        category2 = Category()
        category2.id=2L
        category2.name="cat2"
        category2.label="Cat2"

        categories = Arrays.asList(category1, category2)

    }

    @Test
    fun shouldShowHomePage() {

        given(this.techFeedService.findAllCategories()).willReturn(categories)

        this.mvc.perform(get("/")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk)
                .andExpect(view().name("index"))
                .andExpect(model().attribute("categories", hasSize<Any>(2)))
        verify(techFeedService).findAllCategories()
    }

    @Test
    fun shouldShowCategoryPage() {
        val category = "java"
        given(this.techFeedService.findCategoryByName(category)).willReturn(Optional.of(category1))
        given(this.techFeedService.filterCategoryLinks(category,"","")).willReturn(links)

        this.mvc.perform(get("/category/$category")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk)
                .andExpect(view().name("category"))
                .andExpect(model().attribute("links", hasSize<Any>(2)))
        verify(techFeedService).filterCategoryLinks(category,"","")
    }

    @Test
    fun shouldShowLinksByCategoryAndTag() {
        val category = "java"
        val tag = "spring"
        given(this.techFeedService.findCategoryByName(category)).willReturn(Optional.of(category1))
        given(this.techFeedService.filterCategoryLinks(category,tag,"")).willReturn(links)

        this.mvc.perform(get("/category/$category?tag=$tag")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk)
                .andExpect(view().name("category"))
                .andExpect(model().attribute("links", hasSize<Any>(2)))
                .andExpect(model().attribute("searchTag", tag))
        verify(techFeedService).filterCategoryLinks(category,tag,"")
    }

    @Test
    fun shouldShowCategorySearchResults() {
        val category = "java"
        val query = "spring"
        given(this.techFeedService.findCategoryByName(category)).willReturn(Optional.of(category1))
        given(this.techFeedService.filterCategoryLinks(category,"",query)).willReturn(links)

        this.mvc.perform(get("/category/$category?query=$query")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk)
                .andExpect(view().name("category"))
                .andExpect(model().attribute("links", hasSize<Any>(2)))
                .andExpect(model().attribute("query", query))
        verify(techFeedService).filterCategoryLinks(category,"",query)
    }

    @Test
    fun shouldCreateLinkWhenDataIsValid() {
        val link = Link()
        given(this.techFeedService.saveLink(any())).willReturn(link)

        val params = LinkedMultiValueMap<String, String>()
        params.add("category.id", "1")
        params.add("title", "My Awesome post")
        params.add("url", "http://host/sample-url")
        params.add("tags", "spring,boot,docker")

        this.mvc.perform(post("/links")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params)
                .with(csrf())
                .with(user("admin@gmail.com").password("admin").roles("USER", "ADMIN"))
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/"))
                .andReturn()
        verify(techFeedService).saveLink(any())
    }
}