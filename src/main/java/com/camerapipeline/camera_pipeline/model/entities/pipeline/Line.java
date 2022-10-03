package com.camerapipeline.camera_pipeline.model.entities.pipeline;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class Line {
    private List<Position>  line;
}
