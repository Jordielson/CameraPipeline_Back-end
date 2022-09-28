package com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi.ModelPdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.ValueParameterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class PdiDTO {
    private int id;
    @NotNull
    private ModelPdiDTO modelPdi;
    private List<ValueParameterDTO> valueParameters;
    @NotNull
    private Integer pipelineId;

}
