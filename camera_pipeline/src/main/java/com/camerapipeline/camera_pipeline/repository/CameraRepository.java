package com.camerapipeline.camera_pipeline.repository;

import com.camerapipeline.camera_pipeline.model.Camera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {
    
}
