package com.camerapipeline.camera_pipeline.model.repository.mosaic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.camerapipeline.camera_pipeline.model.entities.mosaic.Mosaic;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface MosaicRepository extends RepositoryAbstract<Mosaic, Integer>  {
    @Override
    @Query(value = "SELECT m FROM Mosaic m WHERE m.user.id = ?1")
    Page<Mosaic> findAll(Pageable pageable, Integer userId);
}
