package com.sivalabs.bookmarker.web.controller;

import com.sivalabs.bookmarker.domain.entity.UserType;
import com.sivalabs.bookmarker.domain.model.CreateUserRequest;
import com.sivalabs.bookmarker.domain.model.UserDTO;
import com.sivalabs.bookmarker.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        log.info("process=get_user, user_id="+id);
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public UserDTO createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        log.info("process=create_user, user_email="+createUserRequest.getEmail());
        UserDTO userDTO = new UserDTO(
                null,
                createUserRequest.getName(),
                createUserRequest.getEmail(),
                createUserRequest.getPassword(),
                null,
                UserType.LOCAL,
                null
        );
        return userService.createUser(userDTO);
    }


}
