package com.camerapipeline.camera_pipeline.repository.camera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.camera.Camera;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {
    
}
