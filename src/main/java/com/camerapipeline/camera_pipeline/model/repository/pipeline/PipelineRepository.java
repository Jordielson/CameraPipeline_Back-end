package com.camerapipeline.camera_pipeline.model.repository.pipeline;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

@Repository
public interface PipelineRepository extends RepositoryAbstract<Pipeline, Integer>  {
    @Override
    @Query(value = "SELECT p FROM Pipeline p WHERE p.user.id = ?1")
    Page<Pipeline> findAll(Pageable pageable, Integer userId);
}
