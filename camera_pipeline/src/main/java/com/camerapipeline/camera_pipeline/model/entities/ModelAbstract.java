package com.camerapipeline.camera_pipeline.model.entities;

import com.camerapipeline.camera_pipeline.model.entities.user.User;

public interface ModelAbstract<ID> {
    public ID getId();
    public void setId(ID id);
    public User getUser();
}
