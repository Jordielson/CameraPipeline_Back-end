package com.camerapipeline.camera_pipeline.presentation.controller;

import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;

public abstract class ControllerAbstract<M extends ModelAbstract<ID>, DTO, ID> {
    protected ServiceAbstract<M, ID> service;
    protected Mapper<M, DTO> mapper;

    public ControllerAbstract(ServiceAbstract<M, ID> service, Mapper<M, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DTO>> getAll(Pageable pageable, Principal principal) {
        Page<DTO> list = mapper.toDTOPage(
            service.getAll(pageable, principal)
        );
        return new ResponseEntity<Page<DTO>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DTO> get(@PathVariable("id") ID id, Principal principal) {
        DTO dto = mapper.toDTO(
            service.getById(
                id,
                principal
            )
        );
        return new ResponseEntity<DTO>(dto, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<DTO> add(@Valid @RequestBody DTO dto, Principal principal) {
        M model = service.create(
            mapper.fromDTO(dto), 
            principal
        );
        DTO response = mapper.toDTO(model);

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(model.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ID id, Principal principal) {
        service.delete(
            id,
            principal
            );
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
	public ResponseEntity<?> search(
			Principal principal,
			@RequestBody DTO search,
			Pageable pageable) {
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
