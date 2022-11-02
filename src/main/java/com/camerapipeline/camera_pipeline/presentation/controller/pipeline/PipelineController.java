package com.camerapipeline.camera_pipeline.presentation.controller.pipeline;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.shared.ValidDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pipeline.PipelineMapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @Operation(summary = "Enable or disable a pipeline's functionality")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Pipeline on or off", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = PipelineDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id or active supplied", 
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
        @ApiResponse(responseCode = "404", description = "Camera not found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @PatchMapping("/{id}/state")
	public ResponseEntity<PipelineDTO> setStatus(
        Principal principal,
        @Parameter(
            name = "id",
            description = "Pipeline ID to be enabled or disabled",
            example = "22",
            required = true
        )
        @PathVariable("id") Integer id,
        @Parameter(
            name = "active",
            description = "Pipeline status whether or not it is working",
            example = "true",
            required = true
        )
        @RequestParam(name="active", required=true) Boolean active) {

        PipelineDTO response = mapper.toDTO(
            ((PipelineService) service)
                .setActive(id, active, principal)
        );

        return new ResponseEntity<>(
            response,
            HttpStatus.OK
        );
    }

    @Operation(summary = "Checks if the pipeline can be added to a pipeline")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully verified", 
            content = { 
                @Content(mediaType = "application/json", 
                    schema = @Schema( implementation = ValidDTO.class)
                ) 
            }
        ),
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
        @ApiResponse(responseCode = "500", 
            description = "Server has encountered a situation with which it does not know", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema( implementation = ExceptionMessage.class))
        ),
    })
    @GetMapping(value = "/verify-addition", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> verifyUsed(
        Principal principal,
        @Parameter(
            name = "parentPipelineID",
            description = "Parent pipeline identifier",
            example = "4",
            required = true
        )
        @RequestParam Integer parentPipelineID,
        @Parameter(
            name = "childPipelineID",
            description = "Children pipeline identifier",
            example = "2",
            required = true
        )
        @RequestParam Integer childPipelineID
        ) {
        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((PipelineService) service).checkAdditionValidity(parentPipelineID, childPipelineID)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}
}
