package com.camerapipeline.camera_pipeline.model.entities.pipeline;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pipeline extends DigitalProcess {

    @NotNull
    private boolean isActive;

    @OneToMany(mappedBy = "pipeline", cascade = CascadeType.REMOVE)
    private List<PDI> PDIList;
}
