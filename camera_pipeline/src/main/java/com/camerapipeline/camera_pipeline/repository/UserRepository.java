package com.camerapipeline.camera_pipeline.repository;

import com.camerapipeline.camera_pipeline.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByEmail(@Param("email") String email);
}
