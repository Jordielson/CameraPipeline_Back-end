package com.camerapipeline.camera_pipeline.model.enums;

public enum ParameterType {
    STRING(0),
    NUMBER(1),
    BOOLEAN(2),
    FILE(3);

    private int value;

    ParameterType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
