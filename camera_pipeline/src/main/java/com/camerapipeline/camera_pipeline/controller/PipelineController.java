package com.camerapipeline.camera_pipeline.controller;

import java.util.List;

import com.camerapipeline.camera_pipeline.model.Pipeline;
import com.camerapipeline.camera_pipeline.services.PipelineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pipeline")
public class PipelineController {
    @Autowired
    PipelineService pipelineService;

    @GetMapping("/")
    public ResponseEntity<List<Pipeline>> getAll() {
        List<Pipeline> pipelines = pipelineService.buscar();
        return new ResponseEntity<List<Pipeline>>(pipelines, HttpStatus.OK);
    }
}
