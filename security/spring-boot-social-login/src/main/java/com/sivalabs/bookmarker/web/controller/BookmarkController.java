package com.sivalabs.bookmarker.web.controller;

import com.sivalabs.bookmarker.domain.model.BookmarkDTO;
import com.sivalabs.bookmarker.domain.service.BookmarkService;
import com.sivalabs.bookmarker.domain.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final SecurityService securityService;

    @GetMapping("/bookmarks")
    public String home(Model model) {
        List<BookmarkDTO> data = bookmarkService.getAllBookmarks();
        model.addAttribute("bookmarksData", data);
        return "home";
    }

    @GetMapping("/bookmarks/new")
    @PreAuthorize("isAuthenticated()")
    public String newBookmarkForm(Model model) {
        model.addAttribute("bookmark", new BookmarkDTO());
        return "add-bookmark";
    }

    @PostMapping("/bookmarks")
    @PreAuthorize("isAuthenticated()")
    public String createBookmark(@Valid @ModelAttribute("bookmark") BookmarkDTO bookmark,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-bookmark";
        }

        bookmark.setCreatedUserId(securityService.loginUserId());
        bookmarkService.createBookmark(bookmark);
        return "redirect:/bookmarks";
    }
}
