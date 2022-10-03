package com.camerapipeline.camera_pipeline.model.entities.input.image;

import javax.persistence.Entity;

import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageData extends PipelineInput {
    private String format;
    private String filePath;
}
