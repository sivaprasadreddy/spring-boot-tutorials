package com.sivalabs.ebuddy.repo;

import com.sivalabs.ebuddy.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("!isAnonymous() and isAuthenticated()")
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Override
    @Query("select t from Todo t where t.createdBy.email=?#{principal.username}")
    Page<Todo> findAll(Pageable pageable);
}
