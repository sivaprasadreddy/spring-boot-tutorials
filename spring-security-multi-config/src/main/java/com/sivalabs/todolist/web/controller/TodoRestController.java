package com.sivalabs.todolist.web.controller;

import com.sivalabs.todolist.entity.Todo;
import com.sivalabs.todolist.repo.TodoRepository;
import com.sivalabs.todolist.repo.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoRestController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Todo> getTodosByUser() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return todoRepository.findByCreatedById(loginUser().getId(), sort);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void saveTodo(@RequestBody Todo todo) {
        todo.setId(null);
        todo.setCreatedBy(loginUser());
        todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }

    private com.sivalabs.todolist.entity.User loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User securityUser = (User) authentication.getPrincipal();
            return userRepository.findByEmail(securityUser.getUsername()).orElse(null);
        }
        return null;
    }
}
