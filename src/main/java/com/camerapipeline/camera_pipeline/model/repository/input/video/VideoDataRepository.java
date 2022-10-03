package com.camerapipeline.camera_pipeline.model.repository.input.video;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;

public interface VideoDataRepository extends JpaRepository<VideoData,UUID> {
    Optional<VideoData> findByName(String fileName);
}
