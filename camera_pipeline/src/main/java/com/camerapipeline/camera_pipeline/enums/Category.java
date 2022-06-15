package com.camerapipeline.camera_pipeline.enums;

public enum Category {
    EDICAO(1),
    PROCESSAMENTO(2);

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
