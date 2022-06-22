package com.camerapipeline.camera_pipeline.dto;

import java.util.Objects;

import com.camerapipeline.camera_pipeline.enums.ParameterType;

import lombok.Data;

@Data
public class ParameterDTO {
    private int id;
    private String name;
    private ParameterType type;
    private int modelPdiId;

    public ParameterDTO() {
    }

    public ParameterDTO(int id, String name, ParameterType type, int modelPdiId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.modelPdiId = modelPdiId;
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

    public int getModelPdiId() {
        return this.modelPdiId;
    }

    public void setModelPdiId(int modelPdiId) {
        this.modelPdiId = modelPdiId;
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

    public ParameterDTO modelPdiId(int modelPdiId) {
        setModelPdiId(modelPdiId);
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
        return id == parameterDTO.id && Objects.equals(name, parameterDTO.name) && Objects.equals(type, parameterDTO.type) && modelPdiId == parameterDTO.modelPdiId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, modelPdiId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", modelPdiId='" + getModelPdiId() + "'" +
            "}";
    }
}
