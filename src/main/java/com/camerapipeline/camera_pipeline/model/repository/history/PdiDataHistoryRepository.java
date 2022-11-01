package com.camerapipeline.camera_pipeline.model.repository.history;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;

public interface PdiDataHistoryRepository extends JpaRepository<PdiDataHistory, UUID>{
    @Query(value = "SELECT p FROM PdiDataHistory p "+
        "WHERE p.digitalProcess.id = :#{#processID}")
    List<PdiDataHistory> findByDigitalProcess(@Param("processID") Integer processID);
}
