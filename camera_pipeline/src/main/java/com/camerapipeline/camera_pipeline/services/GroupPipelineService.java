package com.camerapipeline.camera_pipeline.services;

import com.camerapipeline.camera_pipeline.repository.GroupPipelineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupPipelineService {
    @Autowired
    GroupPipelineRepository groupRepository;
}
