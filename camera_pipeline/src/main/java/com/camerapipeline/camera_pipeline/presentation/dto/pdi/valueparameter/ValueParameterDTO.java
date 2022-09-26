package com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter.ParameterDTO;

import lombok.Data;

@Data
public class ValueParameterDTO {
    private int id;
    private String value;
    @NotNull
    private ParameterDTO parameter;

    public ValueParameterDTO() {
    }

    public ValueParameterDTO(int id, String value, ParameterDTO parameter) {
        this.id = id;
        this.value = value;
        this.parameter = parameter;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ParameterDTO getParameter() {
        return this.parameter;
    }

    public void setParameter(ParameterDTO parameter) {
        this.parameter = parameter;
    }

    public ValueParameterDTO id(int id) {
        setId(id);
        return this;
    }

    public ValueParameterDTO value(String value) {
        setValue(value);
        return this;
    }

    public ValueParameterDTO parameter(ParameterDTO parameter) {
        setParameter(parameter);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ValueParameterDTO)) {
            return false;
        }
        ValueParameterDTO valueParameterDTO = (ValueParameterDTO) o;
        return id == valueParameterDTO.id && Objects.equals(value, valueParameterDTO.value) && Objects.equals(parameter, valueParameterDTO.parameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, parameter);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", value='" + getValue() + "'" +
            ", parameter='" + getParameter() + "'" +
            "}";
    }   
}
