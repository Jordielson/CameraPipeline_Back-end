package com.camerapipeline.camera_pipeline.repository.pdi;

import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.repository.RepositoryAbstract;

@Repository
public interface ValueParameterRepository extends RepositoryAbstract<ValueParameter, Integer> {
    
}
