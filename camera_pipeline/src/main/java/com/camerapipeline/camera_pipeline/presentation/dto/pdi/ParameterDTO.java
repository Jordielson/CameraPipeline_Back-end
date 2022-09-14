package com.camerapipeline.camera_pipeline.presentation.dto.pdi;

import java.util.Objects;

import com.camerapipeline.camera_pipeline.model.enums.ParameterType;

import lombok.Data;

@Data
public class ParameterDTO {
    private int id;
    private String name;
    private ParameterType type;
    private boolean required;
    private Integer index;

    public ParameterDTO() {
    }

    public ParameterDTO(int id, String name, ParameterType type, boolean required, Integer index) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.required = required;
        this.index = index;
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

    public ParameterType getType() {
        return this.type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public boolean getRequired() {
        return this.required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public ParameterDTO id(int id) {
        setId(id);
        return this;
    }

    public ParameterDTO name(String name) {
        setName(name);
        return this;
    }

    public ParameterDTO type(ParameterType type) {
        setType(type);
        return this;
    }
    public ParameterDTO required(boolean required) {
        setRequired(required);
        return this;
    }

    public ParameterDTO index(Integer index) {
        setIndex(index);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ParameterDTO)) {
            return false;
        }
        ParameterDTO parameterDTO = (ParameterDTO) o;
        return id == parameterDTO.id && Objects.equals(name, parameterDTO.name) && Objects.equals(type, parameterDTO.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, required, index);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", required='" + getRequired() + "'" +
            ", index='" + getIndex() + "'" +
            "}";
    }
}
