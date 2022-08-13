package com.sivalabs.bookmarker.domain.service;

import com.sivalabs.bookmarker.domain.entity.Bookmark;
import com.sivalabs.bookmarker.domain.mappers.BookmarkMapper;
import com.sivalabs.bookmarker.domain.model.BookmarkDTO;
import com.sivalabs.bookmarker.domain.repository.BookmarkRepository;
import com.sivalabs.bookmarker.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)
    public List<BookmarkDTO> getAllBookmarks()  {
        return bookmarkRepository.findAll()
                .stream().map(bookmarkMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookmarkDTO createBookmark(BookmarkDTO bookmarkDTO) {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(null);
        bookmark.setUrl(bookmarkDTO.getUrl());
        bookmark.setTitle(bookmarkDTO.getTitle());
        bookmark.setCreatedBy(userRepository.getReferenceById(bookmarkDTO.getCreatedUserId()));
        bookmark.setCreatedAt(LocalDateTime.now());
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return bookmarkMapper.toDTO(savedBookmark);
    }

}
