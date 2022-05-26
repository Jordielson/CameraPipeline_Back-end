package com.camerapipeline.camera_pipeline.repository;

import com.camerapipeline.camera_pipeline.model.Pipeline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Integer>  {
    
}
