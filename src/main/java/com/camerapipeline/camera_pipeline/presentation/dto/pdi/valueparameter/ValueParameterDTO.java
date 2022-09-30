package com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter;

import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter.ParameterDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class ValueParameterDTO {
    @Schema(
        title = "ValueParameter identifier",
        name = "id",
        type = "int",
        example = "25",
		required = false
    )
    private int id;
    @Schema(
        name = "value",
        description = "Value that will be sent by the pdi parameter",
        type = "string",
        example = "480x360",
		required = false
    )
    private String value;
    @NotNull
    private ParameterDTO parameter;
}
