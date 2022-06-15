package com.camerapipeline.camera_pipeline.enums;

public enum ParameterTypes {
    STRING(1),
    NUMBER(2),
    BOOL(3);

    private int value;

    ParameterTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
