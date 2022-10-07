package com.camerapipeline.camera_pipeline.model.entities.mosaic;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;

import lombok.Data;

@Data
@Entity
public class MosaicPipeline {
    @Data
    @Embeddable
    public static class Pk implements Serializable {
        @Column(nullable = false, updatable = false)
        private Integer pipelineId;

        @Column(nullable = false, updatable = false)
        private UUID cameraId;

        @Column(nullable = false, updatable = false)
        private Integer mosaicId;
    }

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @JoinColumn(name = "pipelineId", insertable = false, updatable = false)
    private Pipeline pipeline;
    @ManyToOne
    @JoinColumn(name = "cameraId", insertable = false, updatable = false)
    private Camera camera;
    @ManyToOne
    @JoinColumn(name = "mosaicId", insertable = false, updatable = false)
    private Mosaic mosaic;

    // getters, setters, equals, hashCode
}
