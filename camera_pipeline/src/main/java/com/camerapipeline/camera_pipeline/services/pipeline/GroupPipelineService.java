package com.camerapipeline.camera_pipeline.services.pipeline;

import com.camerapipeline.camera_pipeline.model.GroupPipeline;
import com.camerapipeline.camera_pipeline.repository.GroupPipelineRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupPipelineService {
    @Autowired
    GroupPipelineRepository groupRepository;

    public GroupPipeline saveGroupPipeline(GroupPipeline groupPipeline) {
        return groupRepository.save(groupPipeline);
    }

    public List<GroupPipeline> getGroupPipelineList() {
        return groupRepository.findAll();
    }

    public GroupPipeline getGroupPipeline(Integer id) {
        return groupRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public GroupPipeline updateGroupPipeline(GroupPipeline groupPipeline) {
        return groupRepository.save(groupPipeline);
    }

    public void deleteGroupPipeline(GroupPipeline groupPipeline) {
        groupRepository.delete(groupPipeline);
    }
}
