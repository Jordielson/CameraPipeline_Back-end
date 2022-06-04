package com.camerapipeline.camera_pipeline.controller;

import com.camerapipeline.camera_pipeline.dto.LoginDto;
import com.camerapipeline.camera_pipeline.dto.UserDto;
import com.camerapipeline.camera_pipeline.model.User;
import com.camerapipeline.camera_pipeline.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> authenticateUser(@Valid @RequestBody LoginDto loginRequest) {
        UserDto userDetails = userService
        .authenticateUserAndGetToken(
            loginRequest.getLogin(), 
            loginRequest.getPassword()
        );
        return ResponseEntity.ok(
            userDetails
        );
    }

    @PostMapping("/password")
    public ResponseEntity<User> changePassword(@RequestParam("oldpassword") String oldPassword, @RequestParam("newpassword") String newPassword, Principal principal) {
        return ResponseEntity.ok(
            this.userService
                .changePassword(
                    oldPassword, 
                    newPassword, 
                    principal
                )
        );
    }

    @GetMapping("/all")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(
            this.userService.getAllUsers(pageable)
        );
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(
            this.userService.getUser(id)
        );
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody @Valid User u) {
        User user = this.userService.createUser(u);
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody User u) {
        User user = this.userService.updateUser(id, u);
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
