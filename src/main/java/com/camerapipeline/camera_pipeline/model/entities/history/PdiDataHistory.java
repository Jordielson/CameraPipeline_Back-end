package com.camerapipeline.camera_pipeline.model.entities.history;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Position;
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

    @Embedded
    private Position position;

    @ElementCollection
    @CollectionTable(name="children_data_history")
    @Column(name="child")
    private List<Integer> children;

    @OneToMany(mappedBy = "pdi", cascade = CascadeType.REMOVE)
    private List<ValueParameterDataHistory> valueParameters;

    @Builder
    public PdiDataHistory(DigitalProcess digitalProcess, PipelineDataHistory pipeline, DataHistoryEnum action, Integer pdiID, Integer index, Position position, List<Integer> children) {
        this.digitalProcess = digitalProcess;
        this.pipeline = pipeline;
        this.action = action;
        this.id = pdiID;
        this.index = index;
        this.position = position;
        this.children = children;
    }
}
