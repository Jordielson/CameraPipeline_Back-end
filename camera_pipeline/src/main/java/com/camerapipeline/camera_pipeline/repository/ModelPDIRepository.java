package com.camerapipeline.camera_pipeline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.ModelPDI;

@Repository
public interface ModelPDIRepository extends JpaRepository<ModelPDI, Integer>  {
    Optional<ModelPDI> findByName(@Param("name") String name);
}
