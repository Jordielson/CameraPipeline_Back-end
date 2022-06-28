package com.camerapipeline.camera_pipeline.repository.pdi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.pdi.ValueParameter;

@Repository
public interface ValueParameterRepository extends JpaRepository<ValueParameter, Integer> {
    
}
