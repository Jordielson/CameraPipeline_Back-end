package com.camerapipeline.camera_pipeline.model.repository.input.video;

import java.util.Optional;
import java.util.UUID;

import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface VideoDataRepository extends RepositoryAbstract<VideoData,UUID> {
    Optional<VideoData> findByName(String fileName);
}
