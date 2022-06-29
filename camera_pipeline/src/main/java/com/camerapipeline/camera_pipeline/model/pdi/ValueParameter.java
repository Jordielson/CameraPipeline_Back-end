package com.camerapipeline.camera_pipeline.model.pdi;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.user.User;

@Entity
public class ValueParameter implements ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String value;

    @Transient
    private Object convertedValue;

    @ManyToOne
    private Parameter parameter;

    @ManyToOne
    private PDI pdi;


    public ValueParameter() {
    }

    public ValueParameter(Integer id, String value, Object convertedValue, Parameter parameter, PDI pdi) {
        this.id = id;
        this.value = value;
        this.convertedValue = convertedValue;
        this.parameter = parameter;
        this.pdi = pdi;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getConvertedValue() {
        return this.convertedValue;
    }

    public void setConvertedValue(Object convertedValue) {
        this.convertedValue = convertedValue;
    }

    public Parameter getParameter() {
        return this.parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public PDI getPdi() {
        return this.pdi;
    }

    public void setPdi(PDI pdi) {
        this.pdi = pdi;
    }

    public ValueParameter id(Integer id) {
        setId(id);
        return this;
    }

    public ValueParameter value(String value) {
        setValue(value);
        return this;
    }

    public ValueParameter convertedValue(Object convertedValue) {
        setConvertedValue(convertedValue);
        return this;
    }

    public ValueParameter parameter(Parameter parameter) {
        setParameter(parameter);
        return this;
    }

    public ValueParameter pdi(PDI pdi) {
        setPdi(pdi);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ValueParameter)) {
            return false;
        }
        ValueParameter valueParameter = (ValueParameter) o;
        return id == valueParameter.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, convertedValue, parameter, pdi);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", value='" + getValue() + "'" +
            ", convertedValue='" + getConvertedValue() + "'" +
            ", parameter='" + getParameter() + "'" +
            ", pdi='" + getPdi() + "'" +
            "}";
    }

    @Override
    public User getUser() {
        return pdi.getUser();
    }
}
