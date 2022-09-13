package com.camerapipeline.camera_pipeline.provider.services.camera;

import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.model.repository.camera.CameraRepository;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.camera.CameraSpecification;

import java.security.Principal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CameraService extends ServiceAbstract<Camera, Integer> {
    public CameraService(CameraRepository repository) {
        super(repository);
    }

    @Override
    public Camera create(Camera model, Principal principal) {
        model.setUser(super.getUserByPrincipal(principal));
        model.setIsActive(true);
        return super.create(model);
    }
    
    @Override
    public Camera update(Integer id, Camera model, Principal principal) {
        model.setUser(super.authService.loadUserByUsername(principal.getName()));
        return super.update(id, model, principal);
    }

    @Override
    protected Specification<Camera> getSpecification(Camera search) {
        return new CameraSpecification(search);
    }
}
