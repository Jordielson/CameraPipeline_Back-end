package com.camerapipeline.camera_pipeline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.services.pipeline.GroupPipelineService;

@RestController
@RequestMapping("/group-pipeline")
public class GroupPipelineController {
    @Autowired
    GroupPipelineService gPipelineService;
}
