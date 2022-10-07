package com.camerapipeline.camera_pipeline.provider.specification.mosaic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.camerapipeline.camera_pipeline.model.entities.mosaic.Mosaic;

public class MosaicSpecification implements Specification<Mosaic> {

    private final Mosaic criteria;

    public MosaicSpecification(Mosaic criteria) {
        this.criteria=criteria;
    }
    
    @Override
    public Predicate toPredicate(Root<Mosaic> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<Predicate>();
        if(criteria.getName()!=null) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
        }
        if(criteria.getId()!=null) {
            predicates.add(cb.equal(root.get("id"), criteria.getId()));
        }
        if(criteria.getUser()!=null) {
            predicates.add(cb.equal(root.get("user"), criteria.getUser()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
