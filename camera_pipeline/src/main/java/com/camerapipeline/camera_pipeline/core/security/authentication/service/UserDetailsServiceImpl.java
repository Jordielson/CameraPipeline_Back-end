package com.camerapipeline.camera_pipeline.core.security.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.repository.UserRepository;


@Service(value = "userManagementService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        Optional<com.camerapipeline.camera_pipeline.model.User> userO =  userRepository.findByEmail(s);
        if(!userO.isPresent()) {
            throw new UsernameNotFoundException("Usuario nao encontrado!");
        }
        com.camerapipeline.camera_pipeline.model.User u = userO.get();
        return new User(u.getUsername(), u.getPassword(), true, true, true, true, u.getAuthorities());
    }
}