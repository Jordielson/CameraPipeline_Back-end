package com.camerapipeline.camera_pipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camerapipeline.camera_pipeline.model.PDI;

public interface PDIRepository extends JpaRepository<PDI, Integer> {
    
}
