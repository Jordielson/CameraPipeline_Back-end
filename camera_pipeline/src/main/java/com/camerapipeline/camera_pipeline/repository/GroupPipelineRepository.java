package com.camerapipeline.camera_pipeline.repository;

import com.camerapipeline.camera_pipeline.model.GroupPipeline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPipelineRepository extends JpaRepository<GroupPipeline, Integer> {
    
}
