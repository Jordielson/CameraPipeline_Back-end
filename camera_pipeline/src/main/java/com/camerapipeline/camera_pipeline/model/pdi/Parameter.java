package com.camerapipeline.camera_pipeline.model.pdi;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.camerapipeline.camera_pipeline.enums.ParameterType;
import com.camerapipeline.camera_pipeline.model.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.user.User;

@Entity
public class Parameter implements ModelAbstract<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private ParameterType type;

    @ManyToOne
    private ModelPDI modelPdi;


    public Parameter() {
    }

    public Parameter(Integer id, String name, ParameterType type, ModelPDI modelPdi) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.modelPdi = modelPdi;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public ModelPDI getModelPdi() {
        return this.modelPdi;
    }

    public void setModelPdi(ModelPDI modelPdi) {
        this.modelPdi = modelPdi;
    }

    public Parameter id(Integer id) {
        setId(id);
        return this;
    }

    public Parameter name(String name) {
        setName(name);
        return this;
    }

    public Parameter type(ParameterType type) {
        setType(type);
        return this;
    }

    public Parameter modelPdi(ModelPDI modelPdi) {
        setModelPdi(modelPdi);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Parameter)) {
            return false;
        }
        Parameter parameter = (Parameter) o;
        return id == parameter.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, modelPdi);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", modelPdi='" + getModelPdi() + "'" +
            "}";
    }

    @Override
    public User getUser() {
        return modelPdi.getUser();
    }
}
