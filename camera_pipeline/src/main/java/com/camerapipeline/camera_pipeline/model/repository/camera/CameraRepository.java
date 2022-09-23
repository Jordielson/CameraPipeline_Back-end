package com.camerapipeline.camera_pipeline.model.repository.camera;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

@Repository
public interface CameraRepository extends RepositoryAbstract<Camera, Integer> {
    
    @Override
    @Query(value = "SELECT * FROM camera c WHERE c.user_id = ?1", nativeQuery = true)
    Page<Camera> findAll(Pageable pageable, Integer userId);

    @Query(value = "SELECT c FROM Camera c WHERE c.name = :name AND c.user.id = :userId")
    Optional<Camera> findByName(@Param("name") String name, @Param("userId") Integer id);
    @Query(value = "SELECT c FROM Camera c WHERE c.URL = :url AND c.user.id = :userId")
    Optional<Camera> findByURL(@Param("url") String url, @Param("userId") Integer id);
}
