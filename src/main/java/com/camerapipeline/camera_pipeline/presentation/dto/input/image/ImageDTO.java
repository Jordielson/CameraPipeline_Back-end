package com.camerapipeline.camera_pipeline.presentation.dto.input.image;

import com.camerapipeline.camera_pipeline.presentation.dto.input.PipelineInputDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO extends PipelineInputDTO {
    private String format;
}
