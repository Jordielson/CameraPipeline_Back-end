package com.camerapipeline.camera_pipeline.enums;

public enum ParameterType {
    STRING(0),
    NUMBER(1),
    BOOL(2);

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
