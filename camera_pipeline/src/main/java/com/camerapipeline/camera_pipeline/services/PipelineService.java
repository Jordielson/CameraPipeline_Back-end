package com.camerapipeline.camera_pipeline.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.camerapipeline.camera_pipeline.model.Pipeline;
import com.camerapipeline.camera_pipeline.repository.PipelineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PipelineService {
    @Autowired
    PipelineRepository pipelineRepository;

    public List<Pipeline> buscar() {
        return pipelineRepository.findAll();
    }

    public Pipeline buscar(int id) {
        return pipelineRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public Pipeline adicionarPipeline(Pipeline pipeline) {
		return pipelineRepository.save(pipeline);
	}
	
	public void removerPipeline(Pipeline pipeline) {
        pipelineRepository.delete(pipeline);
    }
}
