package com.camerapipeline.camera_pipeline.presentation.controller.input.image;

import java.net.URI;
import java.security.Principal;
import java.util.Base64;
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
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.ProcessPipelineDTO;
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.mapper.input.image.ImageMapper;
import com.camerapipeline.camera_pipeline.provider.mapper.pipeline.PipelineMapper;
import com.camerapipeline.camera_pipeline.provider.services.http.Client;
import com.camerapipeline.camera_pipeline.provider.services.input.image.ImageDataService;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;
import com.camerapipeline.camera_pipeline.provider.utils.files.BASE64DecodedMultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
    private PipelineService pipeService;
    @Autowired
    private PipelineMapper pipeMapper;
    
    @Autowired
    private Client client;

    @Autowired
    private ImageMapper mapper;

    @Operation(summary = "Apply pipeline to image")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully applied"),
        @ApiResponse(responseCode = "400", description = "Invalid multipart form data", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "404", description = "Pipeline with id supplied not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PostMapping(value = "/generateImage")
    public ResponseEntity<ImageDTO> generateImage(
        @RequestParam(value = "image") MultipartFile file,
        @RequestParam(value = "pipeline") String pipelineId,
        Principal principal) {
            PipelineDTO pipe = pipeMapper.toDTO(
                pipeService.getById(
                    Integer.parseInt(pipelineId)
                )
            );

            ImageDTO image = mapper.toDTO(
                service.uploadImage(file, principal)
            );

            ProcessPipelineDTO process = ProcessPipelineDTO.builder()
                .input(image.getUrl())
                .pipeline(pipe)
                .build();

            try {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(process);
                
                String data = client.post(pipe.getUrl(), json);
                byte[] decodedString = Base64.getDecoder().decode(data);
                MultipartFile imageFile = new BASE64DecodedMultipartFile(decodedString, file.getOriginalFilename(), file.getContentType());

                ImageDTO processedImage = mapper.toDTO(
                    service.uploadImage(imageFile, principal)
                );
                service.deleteImage(image.getId(), principal);
                return ResponseEntity.status(HttpStatus.OK)
                    .body(processedImage);
            } catch (JsonProcessingException e) {
                throw new BusinessException("Problems encountered when processing JSON content");
            }
            
    }

    @Operation(summary = "Upload image")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully saved"),
        @ApiResponse(responseCode = "400", description = "Invalid multipart form data", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
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

    @Operation(summary = "Download image")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully send"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "404", description = "Image with id supplied not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @GetMapping(value = "/storage/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> downloadImage(
        @PathVariable UUID id
    ) {
        byte[] imageData = service.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(imageData);
    }

    @Operation(summary = "Get image data")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "404", description = "Image with id supplied not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
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

    @Operation(summary = "Update image data")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully saved"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "404", description = "Image with id supplied not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
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

    @Operation(summary = "Delete image")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully deleted"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "401", description = "Unauthorized", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "403", description = "Access denied", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "404", description = "Image with id supplied not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
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

    @Operation(summary = "Get all images")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully"),
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
