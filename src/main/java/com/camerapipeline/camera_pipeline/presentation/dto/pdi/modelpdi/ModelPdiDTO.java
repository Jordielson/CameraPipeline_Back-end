package com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.model.enums.Category;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter.ParameterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class ModelPdiDTO {
    private int id;
    @NotBlank
    @Size(max = 60)
    private String name;
    @NotBlank
    private String URL;
    private List<ParameterDTO> parameters;
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
