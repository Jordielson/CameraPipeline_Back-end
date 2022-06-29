package com.camerapipeline.camera_pipeline.services.camera;

import com.camerapipeline.camera_pipeline.model.camera.Camera;
import com.camerapipeline.camera_pipeline.model.user.User;
import com.camerapipeline.camera_pipeline.repository.camera.CameraRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.services.user.UserService;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraService extends ServiceAbstract<Camera, Integer> {
    public CameraService(CameraRepository repository) {
        super(repository);
    }
    @Autowired
    UserService userService;

    public Camera create(Camera model, Principal principal) {
        model.setUser(userService.loadUserByUsername(principal.getName()));
        return super.create(model);
    }
    
    public Camera update(Integer id, Camera model, Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());
        repository.findById(id).map(existing -> {
            if(existing.getUser().equals(user)) {
                return existing;
            } else {
                throw new EntityNotFoundException();
            }
        }).orElseThrow(() -> new EntityNotFoundException(""));
        model.setUser(user);
        return super.update(id, model);
    }
}
