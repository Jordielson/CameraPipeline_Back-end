package com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FileDataDTO {
    @Schema(
        title = "Identifier file",
        name = "id",
        type = "uuid",
		required = false
    )
    protected UUID id;
    @Schema(
		name = "name",
		type = "string",
		required = true
	)
    private String name;
    @Schema(
		name = "name",
		type = "string",
		required = true
	)
    private String format;
}
