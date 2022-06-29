package com.camerapipeline.camera_pipeline.services.camera;

import com.camerapipeline.camera_pipeline.model.camera.Camera;
import com.camerapipeline.camera_pipeline.repository.camera.CameraRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

import java.security.Principal;

import org.springframework.stereotype.Service;

@Service
public class CameraService extends ServiceAbstract<Camera, Integer> {
    public CameraService(CameraRepository repository) {
        super(repository);
    }

    @Override
    public Camera create(Camera model, Principal principal) {
        model.setUser(super.userService.loadUserByUsername(principal.getName()));
        return super.create(model);
    }
    
    @Override
    public Camera update(Integer id, Camera model, Principal principal) {
        model.setUser(super.userService.loadUserByUsername(principal.getName()));
        return super.update(id, model, principal);
    }
}
