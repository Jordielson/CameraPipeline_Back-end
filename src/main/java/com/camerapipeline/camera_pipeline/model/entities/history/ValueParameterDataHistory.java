package com.camerapipeline.camera_pipeline.model.entities.history;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
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
public class ValueParameterDataHistory extends DataHistory {
    @NotNull
    private String value;

    @ManyToOne
    private Parameter parameter;

    @ManyToOne
    private PdiDataHistory pdi;

    @Builder
    public ValueParameterDataHistory(String value, Parameter parameter, PdiDataHistory pdi, DataHistoryEnum action, Integer valueID) {
        this.value = value;
        this.parameter = parameter;
        this.pdi = pdi;
        this.action = action;
        this.id = valueID;
    }
}
