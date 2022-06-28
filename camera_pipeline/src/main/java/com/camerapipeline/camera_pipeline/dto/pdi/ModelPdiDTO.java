package com.camerapipeline.camera_pipeline.dto.pdi;

import java.util.List;
import java.util.Objects;

import com.camerapipeline.camera_pipeline.enums.Category;

import lombok.Data;

@Data
public class ModelPdiDTO {
    private int id;
    private String name;
    private String URL;
    private List<ParameterDTO> parameters;
    private Category category;

    public ModelPdiDTO() {
    }

    public ModelPdiDTO(int id, String name, String URL, List<ParameterDTO> parameters, Category category) {
        this.id = id;
        this.name = name;
        this.URL = URL;
        this.parameters = parameters;
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<ParameterDTO> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<ParameterDTO> parameters) {
        this.parameters = parameters;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ModelPdiDTO)) {
            return false;
        }
        ModelPdiDTO modelPdiDTO = (ModelPdiDTO) o;
        return id == modelPdiDTO.id && Objects.equals(name, modelPdiDTO.name) && Objects.equals(URL, modelPdiDTO.URL) && Objects.equals(parameters, modelPdiDTO.parameters) && Objects.equals(category, modelPdiDTO.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, URL, parameters, category);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", URL='" + getURL() + "'" +
            ", parameters='" + getParameters() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
}
