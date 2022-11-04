package com.camerapipeline.camera_pipeline.provider.services.pdi;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.camerapipeline.camera_pipeline.model.entities.files.FileData;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.repository.file.FileDataRepository;
import com.camerapipeline.camera_pipeline.model.repository.pdi.ValueParameterRepository;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.FileDataDTO;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.exception.file.CustomIOException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.pdi.ValueParameterSpecification;

@Service
public class ValueParameterService extends ServiceAbstract<ValueParameter, Integer> {
    @Autowired
    ParameterService paramService;
    @Autowired
    FileDataRepository fileRepository;

    @Value("${CameraPipeline.files-folder.path}")
    private String FOLDER_PATH;
    
    public ValueParameterService(ValueParameterRepository repository) {
        super(repository);
    }

    @Transactional
    public FileDataDTO uploadFile(MultipartFile file, Principal principal) {
        FileData fileData = new FileData();
        fileData.setName(file.getOriginalFilename());
        fileData.setFormat(file.getContentType()); 
        fileData.setUser(getUserByPrincipal(principal));
        
        fileRepository.save(fileData);

        String filePath = String.format(
            "%s/%s.%s", 
            FOLDER_PATH, 
            fileData.getId(),
            getFormat(file.getContentType())
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
        
        return new FileDataDTO(fileData.getId(), fileData.getName(), fileData.getFormat());
    }

    public String getFormat(String contentType) {
        return contentType.split("/")[1];
    }

    public byte[] downloadFile(UUID id) {
        return null;
    }

    @Override
    public ValueParameter create(ValueParameter model) {
        model.setParameter(paramService.getById(model.getParameter().getId()));
        return super.create(model);
    }

    @Override
    protected Specification<ValueParameter> getSpecification(ValueParameter search) {
        return new ValueParameterSpecification(search);
    }    
    
    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new CustomEntityNotFoundException("Value Parameter", id.toString());
    }
}
