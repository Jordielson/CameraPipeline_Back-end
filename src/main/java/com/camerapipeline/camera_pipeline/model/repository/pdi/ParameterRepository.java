package com.camerapipeline.camera_pipeline.model.repository.pdi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface ParameterRepository extends RepositoryAbstract<Parameter, Integer> {
    
    @Override
    @Query(value = "SELECT p, m FROM Parameter p, ModelPDI m "+
        "WHERE m.id = p.modelPdi.id AND m.user.id = ?1")
    Page<Parameter> findAll(Pageable pageable, Integer userId);
}
