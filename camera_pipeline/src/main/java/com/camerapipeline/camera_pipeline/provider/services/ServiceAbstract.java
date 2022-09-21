package com.camerapipeline.camera_pipeline.provider.services;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;

/* 
 * M -> Model
 * ID -> Identifier to model
 */
public abstract class ServiceAbstract<M extends ModelAbstract<ID>, ID> {
    @Autowired
    protected AuthService authService;

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
            getUserByPrincipal(principal).getId()
        );
    }

    public M getById(ID id) {
        return repository.findById(id)
        .orElseThrow(() -> throwNotFoundEntity(id));
    }

    public M getById(ID id, Principal principal) {
        User user = getUserByPrincipal(principal);
        M model = repository.findById(id).map(existing -> {
            if(existing.getUser().equals(user)) {
                return existing;
            } else {
                throw throwNotFoundEntity(id);
            }
        }).orElseThrow(() -> throwNotFoundEntity(id));
        return model;
    }

    public M update(ID id, M model, Principal principal) {
        User user = getUserByPrincipal(principal);
        return repository.findById(id).map(existing -> {
            if(existing.getUser().equals(user)) {
                model.setId(id);
                return repository.save(model);
            } else {
                throw throwNotFoundEntity(id);
            }
        }).orElseThrow(() -> throwNotFoundEntity(id));
    }

    public M delete(ID id, Principal principal) {
        User user = getUserByPrincipal(principal);
        return repository.findById(id).map(existing -> {
            if(existing.getUser().equals(user)) {
                repository.delete(existing);
                return existing;
            } else {
                throw throwNotFoundEntity(id);
            }
        }).orElseThrow(() -> throwNotFoundEntity(id));
    }

    protected User getUserByPrincipal(Principal principal) {
        return authService.loadUserByUsername(principal.getName());
    }

    public Page<M> search(Pageable pageable, Principal principal, M search) {
		User user = getUserByPrincipal(principal);        
        search.setUser(user);            
        return repository.findAll(getSpecification(search), pageable);
	}

    abstract protected Specification<M> getSpecification(M search);
    abstract protected EntityNotFoundException throwNotFoundEntity(ID id);
}
