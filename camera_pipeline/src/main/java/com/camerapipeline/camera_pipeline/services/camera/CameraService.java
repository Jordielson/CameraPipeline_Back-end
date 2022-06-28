package com.camerapipeline.camera_pipeline.services.camera;

import com.camerapipeline.camera_pipeline.model.camera.Camera;
import com.camerapipeline.camera_pipeline.repository.camera.CameraRepository;
import com.camerapipeline.camera_pipeline.services.user.UserService;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraService {
    @Autowired
    CameraRepository cameraRepository;
    @Autowired
    UserService userService;

    public Camera saveCamera(Camera camera) {
        camera.setUser(userService.getUser(1));
        return cameraRepository.save(camera);
    }

    public List<Camera> getCameraList() {
        return cameraRepository.findAll();
    }

    public Camera getCamera(Integer id) {
        return cameraRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public Camera updateCamera(int id, Camera camera) {
        camera.setId(id);
        camera.setUser(userService.getUser(1));
        return cameraRepository.save(camera);
    }

    public void deleteCamera(int id) {
        cameraRepository.delete(getCamera(id));
    }
}
