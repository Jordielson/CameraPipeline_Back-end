package com.camerapipeline.camera_pipeline.services;

import com.camerapipeline.camera_pipeline.core.security.config.TokenProvider;
import com.camerapipeline.camera_pipeline.exception.UserNotFoundException;
import com.camerapipeline.camera_pipeline.model.User;
import com.camerapipeline.camera_pipeline.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;

    public String authenticateUserAndGetToken(String login, String password) {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }

    public User changePassword(String oldPassword, String newPassword, Principal principal) {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal.getName(), oldPassword));

        if (authentication.isAuthenticated()) {
            return this.userRepository.findByEmail(principal.getName()).map(existing -> {
                existing.setPassword(passwordEncoder.encode(newPassword));
                return this.userRepository.save(existing);
            }).orElseThrow(SecurityException::new);
        }
        throw new SecurityException();
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUser(Integer id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User u) {
        u.setId(null);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return this.userRepository.save(u);
    }

    public User updateUser(Integer id, User u) {
        return this.userRepository.findById(id)
                .map(existing -> {
                    u.setId(id);
                    u.setPassword(existing.getPassword());
                    return this.userRepository.save(u);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User deleteUser(Integer id) {
        return this.userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
    
}
