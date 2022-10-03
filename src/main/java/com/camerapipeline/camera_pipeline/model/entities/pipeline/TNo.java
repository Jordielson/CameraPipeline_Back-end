package com.camerapipeline.camera_pipeline.model.entities.pipeline;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class TNo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PDI dado;
    private List<TNo> next;
    private Metadata metadata; 
}
