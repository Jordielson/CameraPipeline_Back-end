package com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.model.enums.Category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DigitalProcessDTO {
    @Schema(
        title = "ModelPDI identifier",
        name = "id",
        type = "int",
        example = "16",
		required = false
    )
    private int id;
    @Schema(
		name = "name",
		example = "image crop",
		type = "string",
		required = true
	)
    @NotBlank
    @Size(max = 60)
    private String name;
    
    @Schema(
		name = "description",
		example = "image crop tool",
		type = "string",
		required = false
	)
    private String description;

    @Schema(
      title = "PDI category (ModelPDI or Pipeline)",
      name = "category",
		  required = true
    )
    private Category category;
}
