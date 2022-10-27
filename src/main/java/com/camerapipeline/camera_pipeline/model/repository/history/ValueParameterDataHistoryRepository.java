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
        "WHERE v.pdi.revision in ("+
            "SELECT p.revision FROM PdiDataHistory p "+
            "WHERE p.digitalProcess.id in :#{#processID}"+
        ")"
    )
    void deleteInBatch(@Param("processID") Integer processID);

    @Modifying
    @Query(value = "DELETE FROM ValueParameterDataHistory v "+
        "WHERE v.pdi.revision in ("+
            "SELECT pdi.revision FROM PdiDataHistory pdi, PipelineDataHistory pipe "+
            "WHERE pdi.pipeline.revision = pipe.revision "+
            "AND pipe.user.id = :#{#userID}"+
        ")"
    )
    void deleteInBatchByUser(@Param("userID") Integer userID);
}
