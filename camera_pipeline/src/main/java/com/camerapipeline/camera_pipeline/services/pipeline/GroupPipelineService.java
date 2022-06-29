package com.camerapipeline.camera_pipeline.services.pipeline;

import com.camerapipeline.camera_pipeline.model.pipeline.GroupPipeline;
import com.camerapipeline.camera_pipeline.repository.pipeline.GroupPipelineRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.services.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupPipelineService extends ServiceAbstract<GroupPipeline, Integer> {
    @Autowired
    UserService userService;

    public GroupPipelineService(GroupPipelineRepository repository) {
        super(repository);
    }
    
    @Override
    public GroupPipeline create(GroupPipeline model) {
        model.setUser(userService.getById(1));
        return super.create(model);
    }

    @Override
    public GroupPipeline update(Integer id, GroupPipeline model) {
        model.setUser(userService.getById(1));
        return super.update(id, model);
    }
}
