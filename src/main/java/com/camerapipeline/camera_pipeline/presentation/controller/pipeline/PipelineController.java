package com.camerapipeline.camera_pipeline.presentation.controller.pipeline;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pipeline.PipelineMapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.security.Principal;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pipeline")
public class PipelineController extends ControllerAbstract<Pipeline, PipelineDTO, Integer> {
    public PipelineController(PipelineService service, PipelineMapper mapper) {
        super(service, mapper);
    }

    @Operation(summary = "View pipeline result")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully visualization"),
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
    @PostMapping("/preview/{id}")
    public ResponseEntity<String> execute(
        @Parameter(
            name = "id",
            description = "ID of the pipeline that will be processed and viewed",
            example = "2",
            required = true
        )
        @PathVariable("id") Integer id, 
        Principal principal) {
        return new ResponseEntity<String>("rtsp://rtsp.stream/pattern", HttpStatus.OK);
    }

    @Operation(summary = "Search a pipeline by name")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully search"),
        @ApiResponse(responseCode = "400", description = "Invalid name supplied", 
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<PipelineDTO>> search(
        Principal principal,
        @Parameter(
            name = "name",
            description = "Query to be search",
            example = "pipe",
            required = true
        )
        @RequestParam String name,
        @ParameterObject Pageable pageable) {
            PipelineDTO search = new PipelineDTO().name(name);
            Page<PipelineDTO> list = mapper.toDTOPage(
                service.search(
                    pageable, 
                    principal, 
                    mapper.fromDTO(search)
                )
            );
            
            return new ResponseEntity<Page<PipelineDTO>>(list, HttpStatus.OK);
	}
}
