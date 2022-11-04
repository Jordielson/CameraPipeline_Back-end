package com.camerapipeline.camera_pipeline.presentation.controller.pdi;

import java.net.URI;
import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.FileDataDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pdi.ValueParameterMapper;
import com.camerapipeline.camera_pipeline.provider.services.pdi.ValueParameterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/value")
public class ValueParameterController {
    @Autowired
    ValueParameterService service;
    @Autowired
    ValueParameterMapper mapper;

    @Operation(summary = "Upload file")
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
    @PostMapping(value = "/storage/upload-file")
    public ResponseEntity<?> uploadImage(
        @RequestParam(value = "file") MultipartFile file,
        Principal principal
        ) {
            FileDataDTO upload = service.uploadFile(file, principal);
            
            URI uri = MvcUriComponentsBuilder.fromController(
                    getClass()
                ).path("/storage/{id}")
                .buildAndExpand(upload.getId()).toUri();
            return ResponseEntity.created(uri).body(upload);
    }

    @Operation(summary = "Download file")
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
    @GetMapping(value = "/storage/{id}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> downloadUUID(
        @PathVariable UUID id
    ) {
        byte[] fileData = service.downloadFile(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(fileData);
    }
}
