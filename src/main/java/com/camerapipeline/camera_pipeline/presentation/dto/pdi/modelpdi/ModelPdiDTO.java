package com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter.ParameterDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.DigitalProcessDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
@Data
public class ModelPdiDTO extends DigitalProcessDTO {
    @Schema(
		name = "URL",
		example = "https://camerapipeline/api/image-crop",
		type = "string",
		required = true
	)
    @NotBlank
    private String url;

    @Schema(
        title = "PDI Parameters",
        name = "parameters",
		required = false
    )
    private List<ParameterDTO> parameters;

    public ModelPdiDTO parameters(List<ParameterDTO> parameters) {
        setParameters(parameters);
        return this;
    }
}
