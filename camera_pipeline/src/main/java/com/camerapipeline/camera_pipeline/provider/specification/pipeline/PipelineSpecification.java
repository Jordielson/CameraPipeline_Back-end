package com.camerapipeline.camera_pipeline.provider.specification.pipeline;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline_;

public class PipelineSpecification implements Specification<Pipeline> {

    private final Pipeline criteria;

    public PipelineSpecification(Pipeline criteria) {
        this.criteria=criteria;
    }

    @Override
    public Predicate toPredicate(Root<Pipeline> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<Predicate>();
        
        if(criteria.getName()!=null) {
            predicates.add(cb.like(cb.lower(root.get(Pipeline_.name)), "%" + criteria.getName().toLowerCase() + "%"));
        }
        if(criteria.getDescription()!=null) {
            predicates.add(cb.like(cb.lower(root.get(Pipeline_.description)), "%" + criteria.getDescription().toLowerCase() + "%"));
        }
        if(criteria.getUser()!=null) {
            predicates.add(cb.equal(root.get(Pipeline_.USER), criteria.getUser()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
