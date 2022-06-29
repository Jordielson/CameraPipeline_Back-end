package com.camerapipeline.camera_pipeline.services;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.camerapipeline.camera_pipeline.model.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.user.User;
import com.camerapipeline.camera_pipeline.repository.RepositoryAbstract;
import com.camerapipeline.camera_pipeline.services.user.AuthService;

/* 
 * M -> Model
 * ID -> Identifier to model
 */
public abstract class ServiceAbstract<M extends ModelAbstract<ID>, ID> {
    @Autowired
    protected AuthService userService;

    protected RepositoryAbstract<M, ID> repository;

    public ServiceAbstract(RepositoryAbstract<M, ID> repository) {
        this.repository = repository;
    }

    public M create(M model, Principal principal) {
        return create(model);
    }

    public M create(M model) {
        model.setId(null);
        return repository.save(model);
    }

    public Page<M> getAll(Pageable pageable, Principal principal) {
        return repository.findAll(
            pageable, 
            userService.loadUserByUsername(principal.getName()).getId()
        );
    }

    public M getById(ID id) {
        return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public M getById(ID id, Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());
        M model = repository.findById(id).map(existing -> {
            if(existing.getUser().equals(user)) {
                return existing;
            } else {
                throw new EntityNotFoundException();
            }
        }).orElseThrow(() -> new EntityNotFoundException(""));
        return model;
    }

    public M update(ID id, M model, Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());
        return repository.findById(id).map(existing -> {
            if(existing.getUser().equals(user)) {
                model.setId(id);
                return repository.save(model);
            } else {
                throw new EntityNotFoundException();
            }
        }).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public M delete(ID id, Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());
        return repository.findById(id).map(existing -> {
            if(existing.getUser().equals(user)) {
                repository.delete(existing);
                return existing;
            } else {
                throw new EntityNotFoundException();
            }
        }).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }
}
