package com.camerapipeline.camera_pipeline.model.repository.pdi;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

@Repository
public interface ModelPDIRepository extends RepositoryAbstract<ModelPDI, Integer>  {
    
    Optional<ModelPDI> findByName(@Param("name") String name);

    @Override
    @Query(value = "SELECT * FROM model_pdi m WHERE m.user_id = ?1", nativeQuery = true)
    Page<ModelPDI> findAll(Pageable pageable, Integer userId);
}
