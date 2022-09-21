package com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.enums.ParameterType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class ParameterDTO {
    private int id;
    @NotBlank
    private String name;
    @NotNull
    private ParameterType type;
    @NotNull
    private Boolean required;
    private Integer index;
}
