package com.camerapipeline.camera_pipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.ModelPDI;

@Repository
public interface ModelPDIRepository extends JpaRepository<ModelPDI, Integer>  {
    
}
