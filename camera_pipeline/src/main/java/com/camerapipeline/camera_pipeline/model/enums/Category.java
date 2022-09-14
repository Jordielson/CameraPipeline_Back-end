package com.camerapipeline.camera_pipeline.model.enums;

public enum Category {
    PDI(1),
    PIPELINE(2);

    private int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
