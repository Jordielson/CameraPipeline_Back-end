package com.camerapipeline.camera_pipeline.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.user.User;
import com.camerapipeline.camera_pipeline.repository.user.UserRepository;

@Service
public class AuthService {
    @Autowired
    UserRepository repository;
    
    public User loadUserByUsername(String name) {
        return repository.findByEmail(name).get();
    }
}