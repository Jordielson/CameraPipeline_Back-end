package com.camerapipeline.camera_pipeline.model.repository.pdi;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

@Repository
public interface ValueParameterRepository extends RepositoryAbstract<ValueParameter, Integer> {
    
    @Modifying
    @Query(value = "DELETE FROM ValueParameter v "+
        "WHERE v.pdi.id in ("+
            "SELECT p.id FROM PDI p "+
            "WHERE p.pipeline.id in :#{#pipelineID}"+
        ")"
    )
    void deleteInBatch(@Param("pipelineID") Integer pipelineID);
}
