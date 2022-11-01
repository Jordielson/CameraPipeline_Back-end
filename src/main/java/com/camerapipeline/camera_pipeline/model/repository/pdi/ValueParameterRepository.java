package com.camerapipeline.camera_pipeline.model.repository.pdi;

import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

@Repository
public interface ValueParameterRepository extends RepositoryAbstract<ValueParameter, Integer> {
}
