package com.camerapipeline.camera_pipeline.repository.pipeline;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.pipeline.GroupPipeline;
import com.camerapipeline.camera_pipeline.repository.RepositoryAbstract;

@Repository
public interface GroupPipelineRepository extends RepositoryAbstract<GroupPipeline, Integer> {
    @Override
    @Query(value = "SELECT * FROM group_pipeline g WHERE g.user_id = ?1", nativeQuery = true)
    Page<GroupPipeline> findAll(Pageable pageable, Integer userId);
}
