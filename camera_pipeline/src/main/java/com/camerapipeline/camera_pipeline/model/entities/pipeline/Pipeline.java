package com.camerapipeline.camera_pipeline.model.entities.pipeline;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pipeline implements ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
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
    @JsonBackReference
    @NotNull
    @ManyToOne
    private GroupPipeline groupPipeline;

    @OneToMany(mappedBy = "pipeline")
    private List<PDI> PDIList;

    @ManyToMany(cascade = CascadeType.REFRESH)
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

    public Pipeline() {
    }

    public Pipeline(Integer id, String name, String description, LocalDateTime creationDate, LocalDateTime modificationTime, boolean isActive, GroupPipeline groupPipeline, List<PDI> PDIList, List<Camera> cameraList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.modificationTime = modificationTime;
        this.isActive = isActive;
        this.groupPipeline = groupPipeline;
        this.PDIList = PDIList;
        this.cameraList = cameraList;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationTime() {
        return this.modificationTime;
    }

    public void setModificationTime(LocalDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }

    public boolean isIsActive() {
        return this.isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public GroupPipeline getGroupPipeline() {
        return this.groupPipeline;
    }

    public void setGroupPipeline(GroupPipeline groupPipeline) {
        this.groupPipeline = groupPipeline;
    }

    public List<PDI> getPDIList() {
        return this.PDIList;
    }

    public void setPDIList(List<PDI> PDIList) {
        this.PDIList = PDIList;
    }

    public List<Camera> getCameraList() {
        return this.cameraList;
    }

    public void setCameraList(List<Camera> cameraList) {
        this.cameraList = cameraList;
    }

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
        setIsActive(isActive);
        return this;
    }

    public Pipeline groupPipeline(GroupPipeline groupPipeline) {
        setGroupPipeline(groupPipeline);
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pipeline)) {
            return false;
        }
        Pipeline pipeline = (Pipeline) o;
        return id == pipeline.id && Objects.equals(name, pipeline.name) && Objects.equals(description, pipeline.description) && Objects.equals(creationDate, pipeline.creationDate) && Objects.equals(modificationTime, pipeline.modificationTime) && isActive == pipeline.isActive && Objects.equals(groupPipeline, pipeline.groupPipeline) && Objects.equals(PDIList, pipeline.PDIList) && Objects.equals(cameraList, pipeline.cameraList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, creationDate, modificationTime, isActive, groupPipeline, PDIList, cameraList);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", modificationTime='" + getModificationTime() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", groupPipeline='" + getGroupPipeline() + "'" +
            ", PDIList='" + getPDIList() + "'" +
            ", cameraList='" + getCameraList() + "'" +
            "}";
    }

    @Override
    public User getUser() {
        return groupPipeline != null ? 
            groupPipeline.getUser() :
            null;
    }
    @Override
    public void setUser(User user) {
        if(groupPipeline == null) {
            groupPipeline = new GroupPipeline().user(user);
        } 
        groupPipeline.setUser(user);
    }
}
