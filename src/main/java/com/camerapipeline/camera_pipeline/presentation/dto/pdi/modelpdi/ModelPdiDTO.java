package com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi;

import java.util.List;

import com.camerapipeline.camera_pipeline.model.enums.Category;
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
        title = "PDI Parameters",
        name = "parameters",
		required = false
    )
    private List<ParameterDTO> parameters;

    @Schema(
        title = "PDI category (ModelPDI or Pipeline)",
        name = "category",
        required = true,
        example = "MODEL_PDI"
    )
    @Override
    public Category getCategory() {
        return Category.MODEL_PDI;
    }

    public ModelPdiDTO parameters(List<ParameterDTO> parameters) {
        setParameters(parameters);
        return this;
    }
}
