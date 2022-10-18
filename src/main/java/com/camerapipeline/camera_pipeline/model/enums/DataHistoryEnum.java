package com.camerapipeline.camera_pipeline.model.enums;

public enum DataHistoryEnum {
    INSERT(0),
    UPDATE(1),
    DELETE(2);

    private int value;

    DataHistoryEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
