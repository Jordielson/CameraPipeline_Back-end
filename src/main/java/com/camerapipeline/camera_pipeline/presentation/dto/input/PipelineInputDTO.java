package com.camerapipeline.camera_pipeline.presentation.dto.input;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public abstract class PipelineInputDTO {
    @Schema(
        title = "Pipeline input identifier",
        name = "id",
        type = "int",
        example = "232",
		required = false
    )
    private UUID id;

    @Schema(
		name = "name",
		example = "Camera Centro, Monteiro-PB",
		type = "string",
		required = true
	)
    @NotBlank
	@Size(max=60)
    private String name;

    private PipelineInputType type;
    
    @Schema(
		name = "url",
		example = "rtsp://rtsp.stream/cameramonteiro23",
        description = "Media file access link",
		type = "string",
		required = true
	)
    @NotBlank
    private String url;

    @Schema(
		name = "location",
		example = "http://localhost:8080/api/camera/cc9d2d56-084f-4a7c-927e-1b2c02dad3a9",
        description = "Access link to file information",
		type = "string",
		required = true
	)
    private String location;


    public PipelineInputDTO id(UUID id) {
        setId(id);
        return this;
    }

    public PipelineInputDTO name(String name) {
        setName(name);
        return this;
    }

    public PipelineInputDTO type(PipelineInputType type) {
        setType(type);
        return this;
    }

    public PipelineInputDTO url(String url) {
        setUrl(url);
        return this;
    }

    public PipelineInputDTO location(String location) {
        setLocation(location);
        return this;
    }
}
