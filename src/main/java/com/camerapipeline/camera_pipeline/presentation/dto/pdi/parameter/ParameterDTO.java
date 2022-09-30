package com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.enums.ParameterType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class ParameterDTO {
    @Schema(
        title = "Parameter identifier",
        name = "id",
        type = "int",
        example = "25",
		required = false
    )
    private int id;
    @Schema(
		name = "name",
		example = "image-size",
		type = "string",
		required = true
	)
    @NotBlank
    private String name;
    @Schema(
		name = "type",
		example = "NUMBER",
		type = "ParameterType",
		required = true
	)
    @NotNull
    private ParameterType type;
    @Schema(
		name = "required",
        description = "If the parameter is mandatory"+
            " for the functioning of the PDI", 
		example = "true",
		type = "boolean",
		required = true
	)
    @NotNull
    private Boolean required;
    @Schema(
		name = "index",
        description = "Parameter position in pdi parameter list", 
		example = "2",
		type = "int",
		required = true
	)
    @NotNull
    private Integer index;
}
