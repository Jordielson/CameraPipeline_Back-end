package com.camerapipeline.camera_pipeline.model.entities.history;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.user.User;
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
public class PipelineDataHistory extends DataHistory {

    @NotBlank
    @Column(length = 60)
    private String name;

    @Column(length = 60)
    private String versionName;

    private String description;

    @NotNull
    private boolean isActive;

    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "pipeline")
    private List<PdiDataHistory> PDIList;

    @Builder
    public PipelineDataHistory(String name, String description, boolean isActive, User user, DataHistoryEnum action, Integer pipelineID) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.user = user;
        this.action = action;
        this.id = pipelineID;
    }

}
