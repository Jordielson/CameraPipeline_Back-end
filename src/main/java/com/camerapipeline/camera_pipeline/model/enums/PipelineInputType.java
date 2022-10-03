package com.camerapipeline.camera_pipeline.model.enums;

public enum PipelineInputType {
    CAMERA(1),
    IMAGE(2),
    VIDEO(3);

    private int value;

    PipelineInputType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
