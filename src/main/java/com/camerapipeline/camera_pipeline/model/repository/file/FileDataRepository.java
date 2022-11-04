package com.camerapipeline.camera_pipeline.model.repository.file;

import java.util.Optional;
import java.util.UUID;

import com.camerapipeline.camera_pipeline.model.entities.files.FileData;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface FileDataRepository extends RepositoryAbstract<FileData,UUID> {
    Optional<FileData> findByName(String fileName);
}
