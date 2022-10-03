package com.camerapipeline.camera_pipeline.provider.services.input.image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.repository.input.image.ImageDataRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.exception.file.CustomIOException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.input.image.ImageSpecification;

@Service
public class ImageDataService extends ServiceAbstract<ImageData, UUID> {
    public ImageDataService(ImageDataRepository repository) {
        super(repository);
    }

    @Value("${CameraPipeline.images-folder.path}")
    private String FOLDER_PATH;

    @Transactional
    public ImageData uploadImage(MultipartFile file, Principal principal) {
        ImageData fileData = new ImageData();
        fileData.setName(file.getOriginalFilename());
        fileData.setFormat(file.getContentType()); 

        fileData = create(fileData, principal);

        String filePath = String.format(
            "%s/%s.%s", 
            FOLDER_PATH, 
            fileData.getId(),
            getImageFormat(file.getContentType())
        );
        
        fileData.setFilePath(filePath);
        
        try {
            file.transferTo(new File(filePath));
        } catch (IllegalStateException | IOException e) {
            throw new CustomIOException(
                "The system environment is not prepared for the requested operation", 
                "ERR_SAVE_FILE",
                fileData.getId().toString(),
                e
            );
        }
        
        return fileData;
    }

    public String getImageFormat(String contentType) {
        return contentType.split("/")[1];
    }

    public byte[] downloadImage(UUID id) {
        byte[] images;

        ImageData fileData = repository.findById(id).map(existing -> existing)
        .orElseThrow(() -> throwNotFoundEntity(id));
        
        try {
            String filePath = fileData.getFilePath();
            images = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new CustomIOException(
                "An attempt is made to access a file that does not exist", 
                "ERR_LOAD_FILE",
                fileData.getId().toString(),
                e
            );
        }        
        return images;
    }

    @Transactional
    public ImageData deleteImage(UUID id, Principal principal) {
        ImageData image = delete(id, principal);

        String filePath = image.getFilePath();

        try {
            Files.delete(new File(filePath).toPath());
        } catch (IOException e) {
            throw new CustomIOException(
                "Could not delete file because it was not found", 
                "ERR_REMOVE_FILE",
                id.toString(),
                e
            );
        }

        return image;
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(UUID id) {
        return new CustomEntityNotFoundException("Image", id.toString());
    }

    @Override
    protected Specification<ImageData> getSpecification(ImageData search) {
        return new ImageSpecification(search);
    }
}
