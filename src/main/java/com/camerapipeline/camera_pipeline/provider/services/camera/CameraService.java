package com.camerapipeline.camera_pipeline.provider.services.camera;

import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.model.repository.camera.CameraRepository;
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.camera.CameraSpecification;

import java.security.Principal;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CameraService extends ServiceAbstract<Camera, Integer> {
    public CameraService(CameraRepository repository) {
        super(repository);
    }

    @Override
    public Camera create(Camera model, Principal principal) {
        validCamera(model, null, principal);

        model.setIsActive(true);
        return super.create(model, principal);
    }
    
    @Override
    public Camera update(Integer id, Camera model, Principal principal) {
        validCamera(model, null, principal);
        
        return super.update(id, model, principal);
    }

    @Override
    protected Specification<Camera> getSpecification(Camera search) {
        return new CameraSpecification(search);
    }

    private void validCamera(Camera model, Integer id, Principal principal) {
        if (!checkValidName(model.getName(), id, principal)) {
            throw new BusinessException(String.format("There is already a camera with the name %s", model.getName()));
        }else if (!checkValidUrl(model.getURL(), id, principal)) {
            throw new BusinessException(String.format("There is already a camera with the url %s", model.getURL()));
        }
    }

    public boolean checkValidName(String name, Integer id, Principal p) {
        Optional<Camera> camOptional 
            = ((CameraRepository) repository).findByNameIgnoreCase(
                name, 
                getUserByPrincipal(p).getId()
            );
            
        return (camOptional.isPresent() 
            && camOptional.get().getId() != id) 
            ? false : true;
    }

    public boolean checkValidUrl(String url, Integer id, Principal p) {
        Optional<Camera> camOptional 
            = ((CameraRepository) repository).findByURL(
                url, 
                getUserByPrincipal(p).getId()
            );
        return (camOptional.isPresent()
            && camOptional.get().getId() != id) 
            ? false : true;
    }

    public Camera setActive(Integer cameraId, Boolean active, Principal principal) {
        Camera camera = getById(cameraId);
        camera.setIsActive(active);
        return update(cameraId, camera, principal);
    }

    public boolean checkIfItUsed(Integer id, Principal principal) {
        Camera camera = getById(id, principal);
        return !camera.getPipelineList().isEmpty();
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new CustomEntityNotFoundException("Camera", id.toString());
    }
}
