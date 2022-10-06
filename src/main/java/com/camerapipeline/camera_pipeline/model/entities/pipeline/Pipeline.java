package com.camerapipeline.camera_pipeline.model.entities.pipeline;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Pipeline implements ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(length = 60)
    private String name;
    private String description;
    @CreatedDate
    @Column(name = "creation_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;
    @LastModifiedDate
    @Column(name = "last_change", columnDefinition = "TIMESTAMP")
    private LocalDateTime modificationTime;
    @NotNull
    private boolean isActive;
    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "pipeline")
    private List<PDI> PDIList;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinTable(name = "camera_pipeline", 
		joinColumns = @JoinColumn(
			name = "pipeline_id", 
			referencedColumnName = "id"
		),
		inverseJoinColumns = @JoinColumn(
			name = "camera_id",
			referencedColumnName = "id"
		),
        uniqueConstraints = { @UniqueConstraint(
            name = "UniquePipelineAndCamera", 
            columnNames = { "pipeline_id", "camera_id" }) 
        }
	)
	private List<Camera> cameraList;

    public Pipeline id(Integer id) {
        setId(id);
        return this;
    }

    public Pipeline name(String name) {
        setName(name);
        return this;
    }

    public Pipeline description(String description) {
        setDescription(description);
        return this;
    }

    public Pipeline creationDate(LocalDateTime creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public Pipeline modificationTime(LocalDateTime modificationTime) {
        setModificationTime(modificationTime);
        return this;
    }

    public Pipeline isActive(boolean isActive) {
        setActive(isActive);
        return this;
    }

    public Pipeline PDIList(List<PDI> PDIList) {
        setPDIList(PDIList);
        return this;
    }

    public Pipeline cameraList(List<Camera> cameraList) {
        setCameraList(cameraList);
        return this;
    }

    @PreRemove
    private void removeAll() {
        for (Camera c : cameraList) {
            c.getPipelineList().remove(this);
        }
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public User getUser() {
        return user;
    }
}
