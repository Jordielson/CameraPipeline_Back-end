package com.camerapipeline.camera_pipeline.model.entities.history;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
@Data
public class PdiDataHistory extends DataHistory {
    
    @NotNull
    @ManyToOne
    private ModelPDI modelPdi;

    @NotNull
    @ManyToOne
    private PipelineDataHistory pipeline;

    @Builder
    public PdiDataHistory(ModelPDI modelPdi, PipelineDataHistory pipeline, DataHistoryEnum action, Integer pdiID) {
        this.modelPdi = modelPdi;
        this.pipeline = pipeline;
        this.action = action;
        this.id = pdiID;
    }
}
