package com.camerapipeline.camera_pipeline.presentation.controller;

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

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;

public abstract class ControllerAbstract<M extends ModelAbstract<ID>, REQUEST, RESPONSE, ID> {
    protected ServiceAbstract<M, ID> service;
    protected Mapper<M, REQUEST, RESPONSE> mapper;

    public ControllerAbstract(ServiceAbstract<M, ID> service, Mapper<M, REQUEST, RESPONSE> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<RESPONSE>> getAll(Pageable pageable, Principal principal) {
        Page<RESPONSE> list = mapper.toDTOPage(
            service.getAll(pageable, principal)
        );
        return new ResponseEntity<Page<RESPONSE>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RESPONSE> get(@PathVariable("id") ID id, Principal principal) {
        RESPONSE dto = mapper.toDTO(
            service.getById(
                id,
                principal
            )
        );
        return new ResponseEntity<RESPONSE>(dto, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<RESPONSE> add(@Valid @RequestBody REQUEST dto, Principal principal) {
        RESPONSE response = mapper.toDTO(
            service.create(
                mapper.fromDTO(dto), 
                principal
            )
        );
        return new ResponseEntity<RESPONSE>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RESPONSE> update(
        @PathVariable("id") ID id, 
        @Valid @RequestBody REQUEST dto, 
        Principal principal) {
            RESPONSE response = mapper.toDTO(
                service.update(
                    id,
                    mapper.fromDTO(dto), 
                    principal
            )
        );
        return new ResponseEntity<RESPONSE>(
            response,
            HttpStatus.OK
        );
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
			@RequestBody REQUEST search,
			Pageable pageable) {
        Page<RESPONSE> list = mapper.toDTOPage(
            service.search(
                pageable, 
                principal, 
                mapper.fromDTO(search)
            )
        );
		
		return new ResponseEntity<Page<RESPONSE>>(list, HttpStatus.OK);
	}
}
