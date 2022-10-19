package com.camerapipeline.camera_pipeline.model.entities.history;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PdiDataHistory extends DataHistory {
    
    @NotNull
    @ManyToOne
    private ModelPDI modelPdi;

    @NotNull
    @ManyToOne
    private PipelineDataHistory pipeline;

    @OneToMany(mappedBy = "pdi")
    private List<ValueParameterDataHistory> valueParameters;

    @Builder
    public PdiDataHistory(ModelPDI modelPdi, PipelineDataHistory pipeline, DataHistoryEnum action, Integer pdiID) {
        this.modelPdi = modelPdi;
        this.pipeline = pipeline;
        this.action = action;
        this.id = pdiID;
    }
}
