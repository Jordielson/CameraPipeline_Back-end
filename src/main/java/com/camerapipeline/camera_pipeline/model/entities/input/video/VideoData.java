package com.camerapipeline.camera_pipeline.model.entities.input.video;

import javax.persistence.Entity;

import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoData extends PipelineInput {
    private String format;
    private String filePath;
}
