package com.camerapipeline.camera_pipeline.repository;

import com.camerapipeline.camera_pipeline.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
}
