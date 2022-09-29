package com.camerapipeline.camera_pipeline.presentation.dto.shared;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidDTO {
    @ApiModelProperty(
        value = "Valided",
        name = "valid",
        dataType = "Boolean",
        example = "false"
    )
    @NotNull
    private Boolean valid;
}
