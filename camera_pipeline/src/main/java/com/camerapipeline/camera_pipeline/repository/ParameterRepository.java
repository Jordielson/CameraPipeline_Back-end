package com.camerapipeline.camera_pipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camerapipeline.camera_pipeline.model.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
    
}
