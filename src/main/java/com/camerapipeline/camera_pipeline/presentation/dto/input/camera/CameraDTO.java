package com.camerapipeline.camera_pipeline.presentation.dto.input.camera;

import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.input.camera.Coordinate;
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

    public CameraDTO isPrivate(Boolean isPrivate) {
        setIsPrivate(isPrivate);
        return this;
    }

    public CameraDTO isActive(Boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public CameraDTO coordinate(Coordinate coordinate) {
        setCoordinate(coordinate);
        return this;
    }

    public CameraDTO fpsLimiter(Integer fpsLimiter) {
        setFpsLimiter(fpsLimiter);
        return this;
    }
}
