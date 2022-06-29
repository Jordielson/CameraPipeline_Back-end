package com.camerapipeline.camera_pipeline.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.ModelAbstract;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

public abstract class ControllerAbstract<M extends ModelAbstract<ID>, DTO, ID> {
    ServiceAbstract<M, ID> service;
    Mapper<M, DTO> mapper;

    public ControllerAbstract(ServiceAbstract<M, ID> service, Mapper<M, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DTO>> getAll(Pageable pageable, Principal principal) {
        List<DTO> list = mapper.toDTOList(
            service.getAll(pageable, principal).toList()
        );
        return new ResponseEntity<List<DTO>>(list, HttpStatus.OK);
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
        DTO response = mapper.toDTO(
            service.create(
                mapper.fromDTO(dto), 
                principal
            )
        );
        return new ResponseEntity<DTO>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable("id") ID id, @Valid @RequestBody DTO dto, Principal principal) {
        DTO response = mapper.toDTO(
            service.update(
                id,
                mapper.fromDTO(dto), 
                principal
            )
        );
        return new ResponseEntity<DTO>(
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
}
