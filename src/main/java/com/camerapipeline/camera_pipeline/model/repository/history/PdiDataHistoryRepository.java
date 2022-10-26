package com.camerapipeline.camera_pipeline.model.repository.history;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;

public interface PdiDataHistoryRepository extends JpaRepository<PdiDataHistory, UUID>{
    @Modifying
    @Query(value = "DELETE FROM PdiDataHistory p "+
        "WHERE p.digitalProcess.id = :#{#processID}")
    void deleteInBatch(@Param("processID") Integer processID);
}
