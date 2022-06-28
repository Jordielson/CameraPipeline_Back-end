package com.camerapipeline.camera_pipeline.services.pipeline;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.repository.pipeline.PipelineRepository;

@Service
public class PipelineService {
    @Autowired
    PipelineRepository pipelineRepository;

    public Pipeline savePipeline(Pipeline pipeline) {
        return pipelineRepository.save(pipeline);
    }

    public List<Pipeline> getPipelineList() {
        return pipelineRepository.findAll();
    }

    public Pipeline getPipeline(Integer id) {
        return pipelineRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public Pipeline updatePipeline(int id, Pipeline pipeline) {
        return pipelineRepository.save(pipeline);
    }

    public void deletePipeline(int id) {
        Pipeline pipeline = getPipeline(id);
        pipelineRepository.delete(pipeline);
    }
}
