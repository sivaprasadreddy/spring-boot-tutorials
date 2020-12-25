package com.sivalabs.todolist.repo;

import com.sivalabs.todolist.entity.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCreatedById(Long userId, Sort sort);
}
