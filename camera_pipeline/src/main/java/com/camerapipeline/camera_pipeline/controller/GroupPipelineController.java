package com.camerapipeline.camera_pipeline.controller;

import com.camerapipeline.camera_pipeline.services.GroupPipelineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group-pipeline")
public class GroupPipelineController {
    @Autowired
    GroupPipelineService gPipelineService;
}
