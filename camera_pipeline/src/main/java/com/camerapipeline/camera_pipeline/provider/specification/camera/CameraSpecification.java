package com.camerapipeline.camera_pipeline.provider.specification.camera;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;

public class CameraSpecification implements Specification<Camera> {

    private final Camera criteria;

    public CameraSpecification(Camera criteria) {
        this.criteria=criteria;
    }
    
    @Override
    public Predicate toPredicate(Root<Camera> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<Predicate>();
        if(criteria.getName()!=null) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
        }
        if(criteria.getIsActive()!=null) {
            predicates.add(cb.equal(root.get("isActive"), criteria.getIsActive()));
        }
        if(criteria.getIsPrivate()!=null) {
            predicates.add(cb.equal(root.get("isPrivate"), criteria.getIsPrivate()));
        }
        if(criteria.getUser()!=null) {
            predicates.add(cb.equal(root.get("user"), criteria.getUser()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
