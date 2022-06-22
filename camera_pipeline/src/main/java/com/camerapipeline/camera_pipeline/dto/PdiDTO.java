package com.camerapipeline.camera_pipeline.dto;

import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class PdiDTO {
    private int id;
    private String name;
    private List<ValueParameterDTO> valueParameters;

    public PdiDTO() {
    }

    public PdiDTO(int id, String name, List<ValueParameterDTO> valueParameters) {
        this.id = id;
        this.name = name;
        this.valueParameters = valueParameters;
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

    public List<ValueParameterDTO> getValueParameters() {
        return this.valueParameters;
    }

    public void setValueParameters(List<ValueParameterDTO> valueParameters) {
        this.valueParameters = valueParameters;
    }

    public PdiDTO id(int id) {
        setId(id);
        return this;
    }

    public PdiDTO name(String name) {
        setName(name);
        return this;
    }

    public PdiDTO valueParameters(List<ValueParameterDTO> valueParameters) {
        setValueParameters(valueParameters);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PdiDTO)) {
            return false;
        }
        PdiDTO pdiDTO = (PdiDTO) o;
        return id == pdiDTO.id && Objects.equals(name, pdiDTO.name) && Objects.equals(valueParameters, pdiDTO.valueParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, valueParameters);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", valueParameters='" + getValueParameters() + "'" +
            "}";
    }
}
