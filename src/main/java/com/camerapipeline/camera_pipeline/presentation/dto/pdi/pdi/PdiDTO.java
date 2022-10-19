package com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi.ModelPdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.ValueParameterDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class PdiDTO {
    @Schema(
        title = "PDI identifier",
        name = "id",
        type = "int",
        example = "65",
		required = false
    )
    private Integer id;
    @Schema(
        title = "Model PDI",
        name = "modelPdi",
		required = true
    )
    @NotNull
    private ModelPdiDTO modelPdi;

    @Schema(
        title = "PDI Value Parameters",
        name = "valueParameters",
		required = false
    )
    private List<ValueParameterDTO> valueParameters;

    @Schema(
        title = "Pipeline identifier",
        name = "pipelineId",
        example = "25",
        type = "int",
		required = true
    )
    @NotNull
    private Integer pipelineId;

}
