package com.camerapipeline.camera_pipeline.presentation.controller.history;

import java.security.Principal;
import java.util.UUID;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pipeline.PipelineMapper;
import com.camerapipeline.camera_pipeline.provider.services.history.PipelineDataHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pipeline-data-history")
public class HistoryController {
    @Autowired
    private PipelineDataHistoryService service;
    @Autowired
    private PipelineMapper mapper;

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
    @GetMapping(value = "/pipeline/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PipelineDTO>> getAll(
        @ParameterObject Pageable pageable, 
        @PathVariable("id") Integer id, 
        Principal principal) {
        Page<PipelineDTO> list = mapper.toDTOPage(
            service.getHistoryByPipeline(pageable, id, principal)
        );
        return new ResponseEntity<Page<PipelineDTO>>(list, HttpStatus.OK);
    }
    
    @Operation(summary = "Get a entity by its id")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully found"),
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
        @ApiResponse(responseCode = "404", description = "Entity not found", 
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
    public ResponseEntity<PipelineDTO> get(
        @Parameter(
            name = "id",
            description = "Entity Integer to fetch",
            example = "253",
            required = true
        )
        @PathVariable("id") UUID id, 
        Principal principal) {
        PipelineDTO dto = mapper.toDTO(
            service.getById(
                id,
                principal
            )
        );
        return new ResponseEntity<PipelineDTO>(dto, HttpStatus.OK);
    }
}
