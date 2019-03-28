package com.sivalabs.ebuddy.web.controller;

import com.sivalabs.ebuddy.entity.User;
import com.sivalabs.ebuddy.model.AuthenticationRequest;
import com.sivalabs.ebuddy.model.AuthenticationResponse;
import com.sivalabs.ebuddy.model.ChangePassword;
import com.sivalabs.ebuddy.repo.UserRepository;
import com.sivalabs.ebuddy.security.SecurityUser;
import com.sivalabs.ebuddy.security.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class AuthenticationController {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/auth/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = ((SecurityUser) authentication.getPrincipal()).getUser();
        String jws = tokenHelper.generateToken(user.getEmail());
        long expiresIn = tokenHelper.getExpiredIn();
        return ResponseEntity.ok(new AuthenticationResponse(jws, expiresIn));
    }

    @PostMapping(value = "/auth/refresh")
    public ResponseEntity<AuthenticationResponse> refreshAuthenticationToken(
            HttpServletRequest request, Principal principal) {

        String authToken = tokenHelper.getToken(request);

        if (authToken != null && principal != null) {
            String refreshedToken = tokenHelper.refreshToken(authToken);
            long expiresIn = tokenHelper.getExpiredIn();
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken, expiresIn));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public User user(Principal user) {
        return this.userRepository.findByEmail(user.getName());
    }

    @PostMapping(value = "/change-password")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody ChangePassword changePassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        log.debug("Re-authenticating user '" + username + "' for password change request.");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, changePassword.getOldPassword()));

        log.debug("Changing password for user '" + username + "'");

        User user = this.userRepository.findByEmail(username);
        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        userRepository.save(user);
    }
}
