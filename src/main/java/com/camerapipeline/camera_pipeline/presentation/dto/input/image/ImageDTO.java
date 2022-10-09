package com.camerapipeline.camera_pipeline.presentation.dto.input.image;

import java.util.UUID;

import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;
import com.camerapipeline.camera_pipeline.presentation.dto.input.PipelineInputDTO;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(
		name = "format",
		example = "image/png",
        description = "Format to image",
		type = "string",
		required = true
	)
    private String format;

    @Schema(
        title = "Image identifier",
        name = "id",
        type = "uuid",
        example = "cc9d2d56-084f-4a7c-927e-1b2c02dad3a9",
		required = false
    )
    @Override
    public UUID getId() {
        return super.getId();
    }

    @Schema(
		name = "name",
		example = "bird-wings-flying-feature.gif",
		type = "string",
		required = true
	)
    @Override
    public String getName() {
        return super.getName();
    }

    @Schema(
		name = "location",
		example = "http://localhost:8080/api/image/cc9d2d56-084f-4a7c-927e-1b2c02dad3a9",
        description = "Access link to file information",
		type = "string",
		required = true
	)
    @Override
    public String getLocation() {
        return super.getLocation();
    }

    @Schema(
		name = "type",
		example = "IMAGE",
        description = "Type to input pipeline",
		type = "string",
		required = true
	)
    @Override
    public PipelineInputType getType() {
        return super.getType();
    }

    @Schema(
		name = "url",
		example = "http://localhost:8080/api/image/storage/cc9d2d56-084f-4a7c-927e-1b2c02dad3a9",
        description = "Media file access link",
		type = "string",
		required = true
	)
    @Override
    public String getUrl() {
        return super.getUrl();
    }
}
