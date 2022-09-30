package com.camerapipeline.camera_pipeline.presentation.dto.shared;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(
        title = "Valided",
        name = "valid",
        type = "Boolean",
        example = "false"
    )
    @NotNull
    private Boolean valid;
}
