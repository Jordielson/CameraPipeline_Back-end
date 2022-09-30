package com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.model.enums.Category;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter.ParameterDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class ModelPdiDTO {
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
		name = "URL",
		example = "https://camerapipeline/api/image-crop",
		type = "string",
		required = true
	)
    @NotBlank
    private String URL;
    @Schema(
        title = "PDI Parameters",
        name = "parameters",
		required = false
    )
    private List<ParameterDTO> parameters;
    @Schema(
        title = "PDI category",
        name = "category",
		required = false
    )
    private Category category;

    public ModelPdiDTO id(int id) {
        setId(id);
        return this;
    }

    public ModelPdiDTO name(String name) {
        setName(name);
        return this;
    }

    public ModelPdiDTO URL(String URL) {
        setURL(URL);
        return this;
    }

    public ModelPdiDTO parameters(List<ParameterDTO> parameters) {
        setParameters(parameters);
        return this;
    }

    public ModelPdiDTO category(Category category) {
        setCategory(category);
        return this;
    }
}
