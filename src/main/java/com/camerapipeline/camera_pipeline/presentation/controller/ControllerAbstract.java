package com.camerapipeline.camera_pipeline.presentation.controller;

import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public abstract class ControllerAbstract<M extends ModelAbstract<ID>, DTO, ID> {
    protected ServiceAbstract<M, ID> service;
    protected Mapper<M, DTO> mapper;

    public ControllerAbstract(ServiceAbstract<M, ID> service, Mapper<M, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
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
    public ResponseEntity<Page<DTO>> getAll(
        @ParameterObject Pageable pageable, 
        Principal principal) {
        Page<DTO> list = mapper.toDTOPage(
            service.getAll(pageable, principal)
        );
        return new ResponseEntity<Page<DTO>>(list, HttpStatus.OK);
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
    public ResponseEntity<DTO> get(
        @Parameter(
            name = "id",
            description = "Entity ID to fetch",
            example = "253",
            required = true
        )
        @PathVariable("id") ID id, 
        Principal principal) {
        DTO dto = mapper.toDTO(
            service.getById(
                id,
                principal
            )
        );
        return new ResponseEntity<DTO>(dto, HttpStatus.OK);
    }
    
    @Operation(summary = "Register a entity")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully save"),
        @ApiResponse(responseCode = "400", description = "Invalid entity supplied", 
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
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> add(
        @Valid @RequestBody DTO dto, 
        Principal principal) {
        M model = service.create(
            mapper.fromDTO(dto), 
            principal
        );
        DTO response = mapper.toDTO(model);

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(model.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Update a entity")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully save"),
        @ApiResponse(responseCode = "400", description = "Invalid entity or id supplied", 
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
        @ApiResponse(responseCode = "404", description = "Entity with id supplied not found", 
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
    public ResponseEntity<DTO> update(
        @Parameter(
            name = "id",
            description = "Entity ID to update",
            example = "253",
            required = true
        )
        @PathVariable("id") ID id, 
        @Valid @RequestBody DTO dto, 
        Principal principal) {
            M model = service.update(
                id,
                mapper.fromDTO(dto), 
                principal
            );
            DTO response = mapper.toDTO(model);

            URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
            return ResponseEntity.created(selfLink).body(response);
    }

    @Operation(summary = "Delete a entity")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully removed"),
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
        @ApiResponse(responseCode = "404", description = "Entity with id supplied not found", 
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
            description = "Entity ID to delete",
            example = "253",
            required = true
        )
        @PathVariable("id") ID id, 
        Principal principal) {
        service.delete(
            id,
            principal
            );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search an entity by criteria")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully search"),
        @ApiResponse(responseCode = "400", description = "Invalid criteria supplied", 
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
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<DTO>> search(
			Principal principal,
			@RequestBody DTO search,
			@ParameterObject Pageable pageable) {
        Page<DTO> list = mapper.toDTOPage(
            service.search(
                pageable, 
                principal, 
                mapper.fromDTO(search)
            )
        );
		
		return new ResponseEntity<Page<DTO>>(list, HttpStatus.OK);
	}
}
