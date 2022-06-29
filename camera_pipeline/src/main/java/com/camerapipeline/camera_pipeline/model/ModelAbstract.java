package com.camerapipeline.camera_pipeline.model;

import com.camerapipeline.camera_pipeline.model.user.User;

public interface ModelAbstract<ID> {
    public ID getId();
    public void setId(ID id);
    public User getUser();
}
