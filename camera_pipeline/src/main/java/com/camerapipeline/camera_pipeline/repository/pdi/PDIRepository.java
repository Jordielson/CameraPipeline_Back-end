package com.camerapipeline.camera_pipeline.repository.pdi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camerapipeline.camera_pipeline.model.pdi.PDI;

public interface PDIRepository extends JpaRepository<PDI, Integer> {
    
}
