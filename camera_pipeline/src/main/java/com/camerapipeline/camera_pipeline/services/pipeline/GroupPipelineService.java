package com.camerapipeline.camera_pipeline.services.pipeline;

import com.camerapipeline.camera_pipeline.model.pipeline.GroupPipeline;
import com.camerapipeline.camera_pipeline.repository.pipeline.GroupPipelineRepository;
import com.camerapipeline.camera_pipeline.services.user.UserService;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupPipelineService {
    @Autowired
    GroupPipelineRepository groupRepository;
    @Autowired
    UserService userService;

    public GroupPipeline saveGroupPipeline(GroupPipeline groupPipeline) {
        groupPipeline.setUser(userService.getUser(1));
        return groupRepository.save(groupPipeline);
    }

    public List<GroupPipeline> getGroupPipelineList() {
        return groupRepository.findAll();
    }

    public GroupPipeline getGroupPipeline(Integer id) {
        return groupRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public GroupPipeline updateGroupPipeline(int id, GroupPipeline groupPipeline) {
        groupPipeline.setUser(userService.getUser(1));
        return groupRepository.save(groupPipeline);
    }

    public void deleteGroupPipeline(int id) {
        groupRepository.delete(getGroupPipeline(id));
    }
}
