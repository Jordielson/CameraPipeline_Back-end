package com.camerapipeline.camera_pipeline.model.entities.history;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;
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

    private Integer index;
    
    @NotNull
    @ManyToOne
    private DigitalProcess digitalProcess;

    @NotNull
    @ManyToOne
    private PipelineDataHistory pipeline;

    @OneToMany(mappedBy = "pdi", cascade = CascadeType.REMOVE)
    private List<ValueParameterDataHistory> valueParameters;

    @Builder
    public PdiDataHistory(DigitalProcess digitalProcess, PipelineDataHistory pipeline, DataHistoryEnum action, Integer pdiID, Integer index) {
        this.digitalProcess = digitalProcess;
        this.pipeline = pipeline;
        this.action = action;
        this.id = pdiID;
        this.index = index;
    }
}