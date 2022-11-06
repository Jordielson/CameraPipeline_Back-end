package com.camerapipeline.camera_pipeline.model.repository.history;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camerapipeline.camera_pipeline.model.entities.history.ValueParameterDataHistory;

public interface ValueParameterDataHistoryRepository extends JpaRepository<ValueParameterDataHistory, UUID>{
    @Modifying
    @Query(value = "DELETE FROM ValueParameterDataHistory v "+
        "WHERE v.parameter.id = :#{#parameterID}")
    void deleteByParameter(@Param("parameterID") Integer parameterID);
}
