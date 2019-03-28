package com.sivalabs.ebuddy.repo;

import com.sivalabs.ebuddy.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("!isAnonymous() and isAuthenticated()")
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Override
    @Query("select b from Bookmark b where b.createdBy.email=?#{principal.username}")
    Page<Bookmark> findAll(Pageable pageable);
}
