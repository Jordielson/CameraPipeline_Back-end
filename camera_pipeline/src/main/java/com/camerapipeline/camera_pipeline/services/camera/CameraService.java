package com.camerapipeline.camera_pipeline.services.camera;

import com.camerapipeline.camera_pipeline.model.camera.Camera;
import com.camerapipeline.camera_pipeline.repository.camera.CameraRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.services.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraService extends ServiceAbstract<Camera, Integer> {
    public CameraService(CameraRepository repository) {
        super(repository);
    }
    @Autowired
    UserService userService;

    @Override
    public Camera create(Camera model) {
        model.setUser(userService.getById(1));
        return super.create(model);
    }
    
    @Override
    public Camera update(Integer id, Camera model) {
        model.setUser(userService.getById(1));
        return super.update(id, model);
    }
}
