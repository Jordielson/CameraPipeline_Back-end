package com.camerapipeline.camera_pipeline.repository.pdi;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.pdi.ModelPDI;

@Repository
public interface ModelPDIRepository extends JpaRepository<ModelPDI, Integer>  {
    Optional<ModelPDI> findByName(@Param("name") String name);
}
