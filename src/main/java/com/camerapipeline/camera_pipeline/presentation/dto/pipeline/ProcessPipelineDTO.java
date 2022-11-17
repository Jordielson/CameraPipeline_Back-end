package com.camerapipeline.camera_pipeline.presentation.dto.pipeline;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
@Builder
public class ProcessPipelineDTO {
    @Schema(
		name = "input",
		example = "http://localhost:8080/api/image/2",
        description = "URL to image", 
		type = "string",
		required = true
	)
    @NotBlank
    private String input;
    @Schema(
		name = "pipeline",
		type = "PipelineDTO",
		required = true
	)
    @NotNull
    private PipelineDTO pipeline;
}
