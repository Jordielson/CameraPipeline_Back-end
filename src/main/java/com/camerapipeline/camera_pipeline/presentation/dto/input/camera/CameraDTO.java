package com.camerapipeline.camera_pipeline.presentation.dto.input.camera;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.input.camera.Coordinate;
import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;
import com.camerapipeline.camera_pipeline.presentation.dto.input.PipelineInputDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CameraDTO extends PipelineInputDTO {
    @Schema(
        name = "isPrivate",
        type = "boolean",
        example = "true",
		required = true
    )
    @NotNull
    private Boolean isPrivate;

    @Schema(
        name = "isActive",
        type = "boolean",
        example = "true",
		required = false
    )
    private Boolean isActive;
    private Coordinate coordinate;
    @Schema(
        name = "fpsLimiter",
        type = "int",
        example = "30",
		required = false
    )
    private Integer fpsLimiter;
    
    @Schema(
        name = "baseCamera",
        type = "CameraDTO",
		required = false
    )
    private CameraDTO baseCamera;

    @Schema(
        title = "Camera identifier",
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
		example = "Camera Centro, Monteiro-PB",
		type = "string",
		required = true
	)
    @Override
    public String getName() {
        return super.getName();
    }

    @Schema(
		name = "location",
		example = "http://localhost:8080/api/camera/cc9d2d56-084f-4a7c-927e-1b2c02dad3a9",
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
		example = "CAMERA",
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
		example = "rtsp://rtsp.stream/cameramonteiro23",
        description = "Media file access link",
		type = "string",
		required = true
	)
    @Override
    public String getUrl() {
        return super.getUrl();
    }
}
