package com.camerapipeline.camera_pipeline.model.entities.input;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PipelineInput implements ModelAbstract<UUID>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
    protected UUID id;

    @Column(length = 60, nullable = false)
    private String name;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    protected ZonedDateTime creationTime;

    @LastModifiedDate
    @Column(nullable = false)
    @UpdateTimestamp
    protected ZonedDateTime modificationTime;

    @NotNull
    @ManyToOne
    protected User user;

    @ManyToMany(mappedBy = "inputList")
    private List<Pipeline> pipelineList;

    @PreRemove
    private void removeAll() {
        for (Pipeline p : pipelineList) {
            p.getInputList().remove(this);
        }
    }

    @Override
    public User getUser() {
        return this.user;
    }
    
    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
