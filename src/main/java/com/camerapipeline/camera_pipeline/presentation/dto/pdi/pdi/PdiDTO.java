package com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.ValueParameterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class PdiDTO {
    private int id;
    @NotBlank
    @Size(max = 60)
    private String name;
    private List<ValueParameterDTO> valueParameters;
    @NotNull
    private Integer pipelineId;

}
