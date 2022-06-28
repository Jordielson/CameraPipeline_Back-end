package com.camerapipeline.camera_pipeline.repository.pipeline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.pipeline.Pipeline;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Integer>  {
    
}
