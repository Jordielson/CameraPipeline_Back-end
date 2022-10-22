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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.presentation.dto.history.PipelineDataHistoryDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.history.PipelineDataHistoryMapper;
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
public class PipelineHistoryController {
    @Autowired
    private PipelineDataHistoryService service;
    @Autowired
    private PipelineDataHistoryMapper pipelineHistoryMapper;
    @Autowired
    private PipelineMapper pipelineMapper;

    @Operation(summary = "Get history of a pipeline")
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
    public ResponseEntity<Page<PipelineDataHistoryDTO>> getAll(
        @ParameterObject Pageable pageable, 
        @Parameter(
            name = "id",
            description = "Pipeline identify to fetch",
            example = "256",
            required = true
        )
        @PathVariable("id") Integer id, 
        Principal principal) {
        Page<PipelineDataHistoryDTO> list = pipelineHistoryMapper.toDTOPage(
            service.getHistoryByPipeline(pageable, id, principal)
        );
        return new ResponseEntity<Page<PipelineDataHistoryDTO>>(list, HttpStatus.OK);
    }
    
    @Operation(summary = "Get from a specific version of the pipeline")
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
        @ApiResponse(responseCode = "404", description = "Historic not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @GetMapping(value = "/{version}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PipelineDataHistoryDTO> get(
        @Parameter(
            name = "version",
            description = "Pipeline version to fetch",
            example = "cc9d2d56-084f-4a7c-927e-1b2c02dad3a9",
            required = true
        )
        @PathVariable("version") UUID id, 
        Principal principal) {
        PipelineDataHistoryDTO dto = pipelineHistoryMapper.toDTO(
            service.getById(
                id,
                principal
            )
        );
        return new ResponseEntity<PipelineDataHistoryDTO>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Restore from a specific version of the pipeline")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully restored"),
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
        @ApiResponse(responseCode = "404", description = "Historic not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PostMapping(value = "/restore/{version}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PipelineDTO> restoreVersion(
        @Parameter(
            name = "version",
            description = "Pipeline version to restore",
            example = "cc9d2d56-084f-4a7c-927e-1b2c02dad3a9",
            required = true
        )
        @PathVariable("version") UUID id, 
        Principal principal) {

        PipelineDTO dto = pipelineMapper.toDTO(
            service.restore(
                pipelineHistoryMapper.convertToPipeline(
                    service.getById(
                        id,
                        principal
                    )
                )
            )
        );
        
        return new ResponseEntity<PipelineDTO>(dto, HttpStatus.OK);
    }
}
