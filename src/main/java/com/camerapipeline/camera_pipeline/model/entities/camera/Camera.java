package com.camerapipeline.camera_pipeline.model.entities.camera;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Entity
public class Camera implements ModelAbstract<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @ManyToOne
    private User user;
    @NotBlank
    @Column(length = 60)
    private String name;
    @NotBlank
    private String URL;
    @NotNull
    private Boolean isPrivate;
    @NotNull
    private Boolean isActive;
    
	@Valid
	@Embedded
	private Coordinate coordinate;

    private Integer fpsLimiter;

    @ManyToMany(mappedBy = "cameraList")
    private List<Pipeline> pipelineList;

    @Override
    public User getUser() {
        return user;
    }
    @Override
    public void setUser(User user){
        this.user = user;
    }

    @PreRemove
    private void removeAll() {
        for (Pipeline p : pipelineList) {
            p.getCameraList().remove(this);
        }
    }
}
