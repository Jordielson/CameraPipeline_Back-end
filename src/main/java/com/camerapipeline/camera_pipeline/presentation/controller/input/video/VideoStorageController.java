package com.camerapipeline.camera_pipeline.presentation.controller.input.video;

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
import com.camerapipeline.camera_pipeline.presentation.dto.input.video.VideoDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.input.video.VideoMapper;
import com.camerapipeline.camera_pipeline.provider.services.input.video.VideoDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;


@RestController
@RequestMapping("/video")
public class VideoStorageController {
    @Autowired
    VideoDataService service;

    @Autowired
    VideoMapper mapper;

    @Operation(summary = "Apply pipeline to video")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully applied"),
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
    @PostMapping(value = "/generateVideo")
    public ResponseEntity<VideoDTO> generateVideo(
        @RequestParam(value = "video") MultipartFile file,
        @RequestParam(value = "pipeline") String pipelineId,
        Principal principal) {
            VideoDTO response = mapper.toDTO(
                service.uploadVideo(file, principal)
            );

            return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Upload video")
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
    @PostMapping(value = "/storage/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoDTO> uploadVideo(
        @RequestParam(value = "video") MultipartFile file,
        Principal principal
        ) {
            
            VideoDTO upload = mapper.toDTO(
                service.uploadVideo(file, principal)
            );
            
            URI uri = MvcUriComponentsBuilder.fromController(
                    getClass()
                ).path("/{id}")
                .buildAndExpand(upload.getId()).toUri();
            return ResponseEntity.created(uri).body(upload);
    }

    @Operation(summary = "Download video")
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
        @ApiResponse(responseCode = "404", description = "video with id supplied not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ), 
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @GetMapping(value = "/storage/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadVideo(
        @PathVariable UUID id
    ) {
        byte[] videoData = service.downloadVideo(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ByteArrayResource(videoData));
    }

    @Operation(summary = "Get video data")
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
        @ApiResponse(responseCode = "404", description = "Video with id supplied not found", 
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
    public ResponseEntity<VideoDTO> getVideo(
        @PathVariable UUID id,
        Principal principal
    ) {
        VideoDTO video = mapper.toDTO(
            service.getById(id, principal)
        );

        return ResponseEntity.status(HttpStatus.OK).body(video);
    }

    @Operation(summary = "Update video")
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
        @ApiResponse(responseCode = "404", description = "Video with id supplied not found", 
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
    public ResponseEntity<VideoDTO> update(
        @Parameter(
            name = "id",
            description = "Video ID to update",
            example = "1825fa58-f7ed-4526-a6d6-d2335e456e96",
            required = true
        )
        @PathVariable("id") UUID id, 
        @Valid @RequestBody VideoDTO dto, 
        Principal principal) {
            VideoDTO response = mapper.toDTO(service.update(
                    id,
                    mapper.fromDTO(dto), 
                    principal
                )
            );

            URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
            return ResponseEntity.created(selfLink).body(response);
    }

    @Operation(summary = "Delete video")
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
        @ApiResponse(responseCode = "404", description = "Video with id supplied not found", 
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
            description = "Video ID to delete",
            example = "1825fa58-f7ed-4526-a6d6-d2335e456e96",
            required = true
        )
        @PathVariable("id") UUID id, 
        Principal principal) {
            service.deleteVideo(
                id,
                principal
            );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all videos")
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
    public ResponseEntity<Page<VideoDTO>> getAll(
        @ParameterObject Pageable pageable, 
        Principal principal) {
        Page<VideoDTO> list = mapper.toDTOPage(
            service.getAll(pageable, principal)
        );
        return new ResponseEntity<Page<VideoDTO>>(list, HttpStatus.OK);
    }
}
