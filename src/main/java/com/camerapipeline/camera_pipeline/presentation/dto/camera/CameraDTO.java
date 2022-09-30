package com.camerapipeline.camera_pipeline.presentation.dto.camera;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.model.entities.camera.Coordinate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CameraDTO {
    @Schema(
        title = "Camera identifier",
        name = "id",
        type = "int",
        example = "232",
		required = false
    )
    private int id;
    @Schema(
		name = "name",
		example = "Camera Centro, Monteiro-PB",
		type = "string",
		required = true
	)
    @NotBlank
	@Size(max=50)
    private String name;
    @Schema(
		name = "URL",
		example = "rtsp://rtsp.stream/cameramonteiro23",
		type = "string",
		required = true
	)
    @NotBlank
    private String URL;
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


    public CameraDTO id(int id) {
        setId(id);
        return this;
    }

    public CameraDTO name(String name) {
        setName(name);
        return this;
    }

    public CameraDTO URL(String URL) {
        setURL(URL);
        return this;
    }

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
