package com.camerapipeline.camera_pipeline.presentation.controller.input.image;

import java.net.URI;
import java.security.Principal;
import java.util.UUID;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.presentation.dto.input.image.ImageDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.input.image.ImageMapper;
import com.camerapipeline.camera_pipeline.provider.services.input.image.ImageDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/image")
public class ImageStorageController {
    @Autowired
    private ImageDataService service;

    @Autowired
    private ImageMapper mapper;

    @PostMapping(value = "/storage/upload")
    public ResponseEntity<ImageDTO> uploadImage(
        @RequestParam(value = "image") MultipartFile file,
        Principal principal
        ) {
            
            ImageDTO upload = mapper.toDTO(
                service.uploadImage(file, principal)
            );
            
            URI uri = MvcUriComponentsBuilder.fromController(
                    getClass()
                ).path("/{id}")
                .buildAndExpand(upload.getId()).toUri();
            return ResponseEntity.created(uri).body(upload);
    }

    @GetMapping(value = "/storage/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> downloadImage(
        @PathVariable UUID id
    ) {
        byte[] imageData = service.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(imageData);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDTO> getImage(
        @PathVariable UUID id,
        Principal principal
    ) {
        ImageDTO image = mapper.toDTO(
            service.getById(id, principal)
        );

        return ResponseEntity.status(HttpStatus.OK).body(image);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDTO> update(
        @Parameter(
            name = "id",
            description = "Image ID to update",
            example = "1825fa58-f7ed-4526-a6d6-d2335e456e96",
            required = true
        )
        @PathVariable("id") UUID id, 
        @Valid @RequestBody ImageDTO dto, 
        Principal principal) {
            ImageDTO response = mapper.toDTO(service.update(
                    id,
                    mapper.fromDTO(dto), 
                    principal
                )
            );

            URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
            return ResponseEntity.created(selfLink).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @Parameter(
            name = "id",
            description = "Image ID to delete",
            example = "1825fa58-f7ed-4526-a6d6-d2335e456e96",
            required = true
        )
        @PathVariable("id") UUID id, 
        Principal principal) {
            service.deleteImage(
                id,
                principal
            );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all entities")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully get"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ImageDTO>> getAll(
        @ParameterObject Pageable pageable, 
        Principal principal) {
        Page<ImageDTO> list = mapper.toDTOPage(
            service.getAll(pageable, principal)
        );
        return new ResponseEntity<Page<ImageDTO>>(list, HttpStatus.OK);
    }
}
