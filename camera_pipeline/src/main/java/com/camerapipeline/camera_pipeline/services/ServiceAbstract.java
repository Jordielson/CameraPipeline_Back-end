package com.camerapipeline.camera_pipeline.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.camerapipeline.camera_pipeline.model.ModelAbstract;

/* 
 * M -> Model
 * ID -> Identifier to model
 */
public abstract class ServiceAbstract<M extends ModelAbstract<ID>, ID> {

    protected JpaRepository<M, ID> repository;

    public ServiceAbstract(JpaRepository<M, ID> repository) {
        this.repository = repository;
    }

    public M create(M model) {
        model.setId(null);
        return repository.save(model);
    }

    public Page<M> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public M getById(ID id) {
        return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public M update(ID id, M model) {
        return this.repository.findById(id)
                .map(existing -> {
                    model.setId(id);
                    return this.repository.save(model);
                }).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public M delete(ID id) {
        return this.repository.findById(id)
                .map(user -> {
                    repository.delete(user);
                    return user;
                }).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }
}
