package com.camerapipeline.camera_pipeline.controller;

import com.camerapipeline.camera_pipeline.services.CameraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camera")
public class CameraController {
    @Autowired
    CameraService cameraService;
}
