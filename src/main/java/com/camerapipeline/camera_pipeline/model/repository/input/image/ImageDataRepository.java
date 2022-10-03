package com.camerapipeline.camera_pipeline.model.repository.input.image;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface ImageDataRepository extends RepositoryAbstract<ImageData,UUID> {
    Optional<ImageData> findByName(String fileName);

    @Override
    @Query(value = "SELECT i FROM ImageData i WHERE i.user.id = ?1")
    Page<ImageData> findAll(Pageable pageable, Integer userId);
}