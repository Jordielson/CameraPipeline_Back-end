package com.camerapipeline.camera_pipeline.repository.camera;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.camera.Camera;
import com.camerapipeline.camera_pipeline.repository.RepositoryAbstract;

@Repository
public interface CameraRepository extends RepositoryAbstract<Camera, Integer> {
    
    @Override
    @Query(value = "SELECT * FROM camera c WHERE c.user_id = ?1", nativeQuery = true)
    Page<Camera> findAll(Pageable pageable, Integer userId);
}
