package com.camerapipeline.camera_pipeline.model.pdi;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.pipeline.Pipeline;

@Entity
public class PDI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @ManyToOne
    private ModelPDI modelPdi;

    @NotNull
    @ManyToOne
    private Pipeline pipeline;

    @OneToMany(mappedBy = "pdi")
    private List<ValueParameter> valueParameters;

    public PDI() {
    }

    public PDI(int id, ModelPDI modelPdi, Pipeline pipeline, List<ValueParameter> valueParameters) {
        this.id = id;
        this.modelPdi = modelPdi;
        this.pipeline = pipeline;
        this.valueParameters = valueParameters;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelPDI getModelPDI() {
        return this.modelPdi;
    }

    public void setModelPDI(ModelPDI modelPdi) {
        this.modelPdi = modelPdi;
    }

    public Pipeline getPipeline() {
        return this.pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public List<ValueParameter> getValueParameters() {
        return this.valueParameters;
    }

    public void setValueParameters(List<ValueParameter> valueParameters) {
        this.valueParameters = valueParameters;
    }

    public PDI id(int id) {
        setId(id);
        return this;
    }

    public PDI modelPdi(ModelPDI modelPdi) {
        setModelPDI(modelPdi);
        return this;
    }

    public PDI pipeline(Pipeline pipeline) {
        setPipeline(pipeline);
        return this;
    }

    public PDI valueParameters(List<ValueParameter> valueParameters) {
        setValueParameters(valueParameters);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PDI)) {
            return false;
        }
        PDI pDI = (PDI) o;
        return id == pDI.id && Objects.equals(modelPdi, pDI.modelPdi) && Objects.equals(pipeline, pDI.pipeline) && Objects.equals(valueParameters, pDI.valueParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelPdi, pipeline, valueParameters);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", modelPdi='" + getModelPDI() + "'" +
            ", pipeline='" + getPipeline() + "'" +
            ", valueParameters='" + getValueParameters() + "'" +
            "}";
    }
}
