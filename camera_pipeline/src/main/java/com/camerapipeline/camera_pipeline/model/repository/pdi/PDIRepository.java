package com.camerapipeline.camera_pipeline.model.repository.pdi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface PDIRepository extends RepositoryAbstract<PDI, Integer> {
    
    @Override
    @Query(value = "SELECT * FROM pdi p JOIN model_pdi m ON m.id = p.model_pdi_id WHERE m.user_id = ?1", nativeQuery = true)
    Page<PDI> findAll(Pageable pageable, Integer userId);
}
