package com.camerapipeline.camera_pipeline.model.repository.history;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;

public interface PipelineDataHistoryRepository extends JpaRepository<PipelineDataHistory, UUID>{
    
    @Query(value = "SELECT p FROM PipelineDataHistory p WHERE p.id = :pipelineId AND p.user.id = :userId")
    Page<PipelineDataHistory> findAllByPipeline(Pageable pageable, @Param("pipelineId") Integer pipelineId, @Param("userId") Integer userId);
}
