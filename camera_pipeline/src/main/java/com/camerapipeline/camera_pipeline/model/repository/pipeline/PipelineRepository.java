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
    @Query(value = "SELECT * FROM pipeline p JOIN group_pipeline g ON g.id = p.group_pipeline_id WHERE g.user_id = ?1", nativeQuery = true)
    Page<Pipeline> findAll(Pageable pageable, Integer userId);
}
