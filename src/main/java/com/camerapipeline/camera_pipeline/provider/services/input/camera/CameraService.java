package com.camerapipeline.camera_pipeline.provider.services.input.camera;

import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.repository.input.camera.CameraRepository;
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.input.camera.CameraSpecification;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CameraService extends ServiceAbstract<Camera, UUID> {
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
    public Camera update(UUID id, Camera model, Principal principal) {
        validCamera(model, id, principal);
        
        return super.update(id, model, principal);
    }

    @Override
    protected Specification<Camera> getSpecification(Camera search) {
        return new CameraSpecification(search);
    }

    private void validCamera(Camera model, UUID id, Principal principal) {
        if (!checkValidName(model.getName(), id, principal)) {
            throw new BusinessException(String.format("There is already a camera with the name %s", model.getName()));
        } else if (!checkValidUrl(model.getUrl(), id, principal)) {
            throw new BusinessException(String.format("There is already a camera with the url %s", model.getUrl()));
        }
    }

    public boolean checkValidName(String name, UUID id, Principal p) {
        Optional<Camera> camOptional 
            = ((CameraRepository) repository).findByNameIgnoreCase(
                name, 
                getUserByPrincipal(p).getId()
            );
            
        return (camOptional.isPresent() 
            && camOptional.get().getId() != id) 
            ? false : true;
    }

    public boolean checkValidUrl(String url, UUID id, Principal p) {
        Optional<Camera> camOptional 
            = ((CameraRepository) repository).findByURL(
                url, 
                getUserByPrincipal(p).getId()
            );
        return (camOptional.isPresent()
            && camOptional.get().getId() != id) 
            ? false : true;
    }

    public Camera setActive(UUID cameraId, Boolean active, Principal principal) {
        Camera camera = getById(cameraId);
        camera.setIsActive(active);
        return update(cameraId, camera, principal);
    }

    public boolean checkIfItValidName(UUID id, Principal principal) {
        // Camera camera = getById(id, principal);
        // return !camera.getPipelineList().isEmpty();

        // TODO: Enquanto nao nao foi criado o mosaico return true
        return true;
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(UUID id) {
        return new CustomEntityNotFoundException("Camera", id.toString());
    }
}
