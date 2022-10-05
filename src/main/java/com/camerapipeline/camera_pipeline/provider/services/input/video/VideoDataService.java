package com.camerapipeline.camera_pipeline.provider.services.input.video;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.model.repository.input.video.VideoDataRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.exception.file.CustomIOException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.input.video.VideoSpecification;

@Service
public class VideoDataService extends ServiceAbstract<VideoData, UUID> {
    public VideoDataService(VideoDataRepository repository) {
        super(repository);
    }

    @Value("${CameraPipeline.videos-folder.path}")
    private String FOLDER_PATH;

    @Transactional
    public VideoData uploadVideo(MultipartFile file, Principal principal) {
        VideoData fileData = new VideoData();
        fileData.setName(file.getOriginalFilename());
        fileData.setFormat(file.getContentType()); 

        fileData = create(fileData, principal);

        String filePath = String.format(
            "%s/%s.%s", 
            FOLDER_PATH, 
            fileData.getId(),
            getVideoFormat(file.getContentType())
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

    public String getVideoFormat(String contentType) {
        return contentType.split("/")[1];
    }

    public byte[] downloadVideo(UUID id) {
        byte[] videos;

        VideoData fileData = repository.findById(id).map(existing -> existing)
        .orElseThrow(() -> throwNotFoundEntity(id));
        
        try {
            String filePath = fileData.getFilePath();
            videos = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new CustomIOException(
                "An attempt is made to access a file that does not exist", 
                "ERR_LOAD_FILE",
                fileData.getId().toString(),
                e
            );
        }        
        return videos;
    }

    @Transactional
    public VideoData deleteVideo(UUID id, Principal principal) {
        VideoData video = delete(id, principal);

        String filePath = video.getFilePath();

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

        return video;
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(UUID id) {
        return new CustomEntityNotFoundException("Video", id.toString());
    }

    @Override
    protected Specification<VideoData> getSpecification(VideoData search) {
        return new VideoSpecification(search);
    }
}
