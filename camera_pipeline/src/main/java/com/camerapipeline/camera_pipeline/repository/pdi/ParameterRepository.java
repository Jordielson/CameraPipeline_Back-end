package com.camerapipeline.camera_pipeline.repository.pdi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.camerapipeline.camera_pipeline.model.pdi.Parameter;
import com.camerapipeline.camera_pipeline.repository.RepositoryAbstract;

public interface ParameterRepository extends RepositoryAbstract<Parameter, Integer> {
    
    @Override
    @Query(value = "SELECT * FROM parameter p JOIN model_pdi m ON m.id = p.model_pdi_id WHERE m.user_id = ?1", nativeQuery = true)
    Page<Parameter> findAll(Pageable pageable, Integer userId);
}
