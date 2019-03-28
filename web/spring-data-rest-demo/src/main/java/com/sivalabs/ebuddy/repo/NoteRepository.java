package com.sivalabs.ebuddy.repo;

import com.sivalabs.ebuddy.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("!isAnonymous() and isAuthenticated()")
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Override
    @Query("select n from Note n where n.createdBy.email=?#{principal.username}")
    Page<Note> findAll(Pageable pageable);
}
