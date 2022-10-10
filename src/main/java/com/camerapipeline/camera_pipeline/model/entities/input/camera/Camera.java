package com.camerapipeline.camera_pipeline.model.entities.input.camera;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Camera extends PipelineInput {
    @NotBlank
    private String url;
    
    @NotNull
    private Boolean isPrivate;

    @NotNull
    private Boolean isActive;
    
	@Valid
	@Embedded
	private Coordinate coordinate;

    private Integer fpsLimiter;


    public static Camera clone(Camera camera) {
        Camera clone = new Camera(
            camera.getUrl(), 
            camera.getIsPrivate(), 
            camera.getIsActive(), 
            camera.getCoordinate(), 
            camera.getFpsLimiter()
        );
        clone.setName(camera.getName());
        clone.setPipeline(camera.getPipeline());
        clone.setUser(camera.getUser());

        return clone;
    }
}
