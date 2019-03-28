package com.sivalabs.ebuddy.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivalabs.ebuddy.entity.Bookmark;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookmarkControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithAnonymousUser
    public void getBookmarksWithAnonymousUser() throws Exception {
        this.mvc.perform(get("/api/bookmarks"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("admin@gmail.com")
    public void createBookmarkSuccessfullyWithUserRole() throws Exception {
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle("sampel title");
        bookmark.setUrl("http://dummy.url");
        ObjectMapper mapper = new ObjectMapper();
        String bookmarkJson = mapper.writeValueAsString(bookmark);
        this.mvc.perform(post("/api/bookmarks")
                .content(bookmarkJson)
                .contentType(APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithAnonymousUser
    public void createBookmarkFailWithAnonymousUser() throws Exception {
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle("sampel title");
        bookmark.setUrl("http://dummy.url");
        ObjectMapper mapper = new ObjectMapper();
        String bookmarkJson = mapper.writeValueAsString(bookmark);
        this.mvc.perform(post("/api/bookmarks")
                .content(bookmarkJson)
                .contentType(APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError());
    }
}
