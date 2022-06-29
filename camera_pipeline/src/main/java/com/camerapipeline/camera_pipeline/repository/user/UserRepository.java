package com.camerapipeline.camera_pipeline.repository.user;

import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.user.User;
import com.camerapipeline.camera_pipeline.repository.RepositoryAbstract;

@Repository
public interface UserRepository extends RepositoryAbstract<User, Integer> {
    
    Optional<User> findByEmail(@Param("email") String email);
}
